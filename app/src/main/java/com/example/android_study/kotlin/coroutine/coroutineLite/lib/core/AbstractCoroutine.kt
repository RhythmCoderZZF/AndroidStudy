package com.bennyhuo.kotlin.coroutines.core

import com.bennyhuo.kotlin.coroutines.*
import com.bennyhuo.kotlin.coroutines.cancel.suspendCancellableCoroutine
import com.bennyhuo.kotlin.coroutines.context.CoroutineName
import com.bennyhuo.kotlin.coroutines.scope.CoroutineScope
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume

abstract class AbstractCoroutine<T>(context: CoroutineContext) : Job, Continuation<T>,
    CoroutineScope {

    protected val state = AtomicReference<CoroutineState>()

    override val context: CoroutineContext

    //协程本身也充当作用域
    override val scopeContext: CoroutineContext
        get() = context

    //1.绑定父协程
    protected val parentJob = context[Job]

    private var parentCancelDisposable: Disposable? = null

    val isCompleted
        get() = state.get() is CoroutineState.Complete<*>

    override val isActive: Boolean
        get() = when (val currentState = state.get()) {
            is CoroutineState.Complete<*>,
            is CoroutineState.Cancelling -> false
            is CoroutineState.CompleteWaitForChildren<*> -> !currentState.isCancelling
            is CoroutineState.InComplete -> true
        }

    init {
        //初始状态为“未完成”
        state.set(CoroutineState.InComplete())

        //2.作为Job累加至上下文。注意：Job在上下文中只允许存在一个，最新的Job实例会替换老的Job。
        this.context = context + this

        //绑定父协程
        parentCancelDisposable = parentJob?.attachChild(this)
    }

    //3.绑定子协程
    override fun attachChild(child: Job): Disposable {

        //将child保存到状态对象的job集合中
        state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).with(child)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).with(child)
                }
                is CoroutineState.CompleteWaitForChildren<*> -> prev.copy().with(child)
                is CoroutineState.Complete<*> -> throw IllegalStateException("Parent already completed.")
            }
        }

        //⭐当自己被取消的时候，也取消子协程。
        return invokeOnCancel {
            child.cancel()
        }
    }

    /*——————————————————————————————resumeWith————————————————————————————————*/

    //4.当协程体正常或异常执行完毕时，会执行到这里。
    override fun resumeWith(result: Result<T>) {
        val newState = state.updateAndGet { prevState ->
            when (prevState) {
                is CoroutineState.Cancelling,

                //尝试进入“完成”状态，若子协程还未完成，则变为“待完成”状态
                is CoroutineState.InComplete -> prevState.tryComplete(result)
                is CoroutineState.CompleteWaitForChildren<*>,
                is CoroutineState.Complete<*> -> {
                    throw IllegalStateException("Already completed!")
                }
            }
        }

        when (newState) {
            //若为“待完成”状态，则向所有子协程注册完成监听器，监听器触发tryCompleteOnChildCompleted方法
            is CoroutineState.CompleteWaitForChildren<*> -> newState.tryWaitForChildren(::tryCompleteOnChildCompleted)
            is CoroutineState.Complete<*> -> makeCompletion(newState as CoroutineState.Complete<T>)
        }

    }

    //5.从“待完成”到“完成”状态的迁移过程，会被多次回调，直到变为“完成”状态
    private fun tryCompleteOnChildCompleted(child: Job) {
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.Cancelling,
                is CoroutineState.InComplete -> {
                    throw IllegalStateException("Should be waiting for children!")
                }
                is CoroutineState.CompleteWaitForChildren<*> -> {
                    //每一个子协程执行完毕后，就会移除子协程，直到都移除了，返回“完成”状态
                    prev.onChildCompleted(child)
                }
                is CoroutineState.Complete<*> -> throw IllegalStateException("Already completed!")
            }
        }

        (newState as? CoroutineState.Complete<T>)?.let {
            makeCompletion(it)
        }
    }

    //6.“完成”状态后的收尾工作。
    private fun makeCompletion(newState: CoroutineState.Complete<T>) {
        val result = if (newState.exception == null) {
            Result.success(newState.value)
        } else {
            Result.failure<T>(newState.exception)
        }

        //若为异常结束，则需要对异常处理
        result.exceptionOrNull()?.let(this::tryHandleException)

        //回调监听自己完成的监听器
        newState.notifyCompletion(result)
        newState.clear()

        //移除监听父协程取消的监听器
        parentCancelDisposable?.dispose()
    }

    //7.异常处理，仅对非取消异常处理
    private fun tryHandleException(e: Throwable): Boolean {
        return when (e) {
            is CancellationException -> {
                false
            }
            else -> {

                //优先将异常抛给Parent处理，没有Parent才会自行处理
                (parentJob as? AbstractCoroutine<*>)?.handleChildException(e)?.takeIf { it }
                    ?: handleJobException(e)
            }
        }
    }

    //接收Child抛出的异常，先取消自己，然后处理异常
    protected open fun handleChildException(e: Throwable): Boolean {
        cancel()
        return tryHandleException(e)
    }

    //一般交给上下文中的异常处理器处理，详见StandaloneCoroutine
    protected open fun handleJobException(e: Throwable) = false

    /*——————————————————————————————join————————————————————————————————*/

    //7.挂起当前所处在的协程，指定自己优先执行
    override suspend fun join() {
        when (state.get()) {
            is CoroutineState.InComplete,
            is CoroutineState.CompleteWaitForChildren<*>,
            is CoroutineState.Cancelling -> return joinSuspend()
            is CoroutineState.Complete<*> -> {
                val currentCallingJobState = coroutineContext[Job]?.isActive ?: return
                if (!currentCallingJobState) {
                    throw CancellationException("Coroutine is cancelled.")
                }
                return
            }
        }
    }

    //挂起所处的协程，当自己执行完毕后通知所处的协程恢复执行。
    private suspend fun joinSuspend() = suspendCancellableCoroutine<Unit> { continuation ->

        //向自己注册完成监听，当完成时恢复当前协程继续执行。
        val disposable = doOnCompleted { result ->
            continuation.resume(Unit)
        }

        //如果当前协程被取消，要立即恢复当前协程执行。不能让当前协程一直等待自己完成。
        continuation.invokeOnCancellation { disposable.dispose() }
    }

    /*——————————————————————————————Cancel————————————————————————————————*/

    //8.取消自己。
    override fun cancel() {
        val prevState = state.getAndUpdate { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.Cancelling().from(prev)
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> prev
                is CoroutineState.CompleteWaitForChildren<*> -> {
                    prev.copy(isCancelling = true)
                }
            }
        }

        //自己还在运行或者已经完成但Child还没完成，需要通知取消监听器
        if (prevState is CoroutineState.InComplete || prevState is CoroutineState.CompleteWaitForChildren<*>) {
            prevState.notifyCancellation()
        }

        //自己被取消后走到生命尽头，因此需要取消在Parent中注册的取消监听器
        parentCancelDisposable?.dispose()
    }

    protected fun doOnCompleted(block: (Result<T>) -> Unit): Disposable {
        val disposable = CompletionHandlerDisposable(this, block)
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).with(disposable)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).with(disposable)
                }
                is CoroutineState.Complete<*> -> {
                    prev
                }
                is CoroutineState.CompleteWaitForChildren<*> -> prev.copy().with(disposable)
            }
        }
        (newState as? CoroutineState.Complete<T>)?.let {
            block(
                when {
                    it.exception != null -> Result.failure(it.exception)
                    it.value != null -> Result.success(it.value)
                    else -> throw IllegalStateException("Won't happen.")
                }
            )
        }
        return disposable
    }

    override fun invokeOnCancel(onCancel: OnCancel): Disposable {
        val disposable = CancellationHandlerDisposable(this, onCancel)
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).with(disposable)
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> {
                    prev
                }
                is CoroutineState.CompleteWaitForChildren<*> -> prev.copy().with(disposable)
            }
        }
        (newState as? CoroutineState.Cancelling)?.let {
            // call immediately when Cancelling.
            onCancel()
        }
        return disposable
    }

    override fun invokeOnCompletion(onComplete: OnComplete): Disposable {
        return doOnCompleted { _ -> onComplete() }
    }

    //异常监听器或者Child
    override fun remove(disposable: Disposable) {
        state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).without(disposable)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).without(disposable)
                }
                is CoroutineState.Complete<*> -> {
                    prev
                }
                is CoroutineState.CompleteWaitForChildren<*> -> prev.copy().without(disposable)
            }
        }
    }

    override fun toString(): String {
        return context[CoroutineName].toString()
    }
}