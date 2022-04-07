package com.bennyhuo.kotlin.coroutines.cancel

import com.bennyhuo.kotlin.coroutines.CancellationException
import com.bennyhuo.kotlin.coroutines.Job
import com.bennyhuo.kotlin.coroutines.OnCancel
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resumeWithException


/**
 * CancellableContinuation是上游Continuation的一个“套壳”。它有以下几个作用：
 * 1.向上游协程返回“挂起”标志或者结果
 * 2.能够响应上游Continuation的取消，当被取消时，立即抛出取消异常，恢复上游Continuation的执行。
 */
class CancellableContinuation<T>(private val continuation: Continuation<T>) : Continuation<T> by continuation {

    //协程运行状态
    private val state = AtomicReference<CancelState>(CancelState.InComplete)

    //当前协程是否挂起——初始为“不确定”
    private val decision = AtomicReference(CancelDecision.UNDECIDED)

    val isCompleted: Boolean
        get() = when (state.get()) {
            CancelState.InComplete,
            is CancelState.CancelHandler -> false
            is CancelState.Complete<*>,
            CancelState.Cancelled -> true
        }

    //1.若协程被挂起，恢复上游Continuation的执行，这里可能返回正常、异常信息；若没有被挂起，直接返回结果
    override fun resumeWith(result: Result<T>) {
        when {
            decision.compareAndSet(CancelDecision.UNDECIDED, CancelDecision.RESUMED) -> {
                // before getResult called.
                state.set(CancelState.Complete(result.getOrNull(), result.exceptionOrNull()))
            }
            decision.compareAndSet(CancelDecision.SUSPENDED, CancelDecision.RESUMED) -> {
                state.updateAndGet { prev ->
                    when (prev) {
                        is CancelState.Complete<*> -> {
                            throw IllegalStateException("Already completed.")
                        }
                        else -> {
                            CancelState.Complete(result.getOrNull(), result.exceptionOrNull())
                        }
                    }
                }
                continuation.resumeWith(result)
            }
        }
    }

    //2.当协程被挂起时返回挂起标志，否则返回结果
    fun getResult(): Any? {
        installCancelHandler()

        if(decision.compareAndSet(CancelDecision.UNDECIDED, CancelDecision.SUSPENDED))
            return COROUTINE_SUSPENDED

        return when (val currentState = state.get()) {
            is CancelState.CancelHandler,
            CancelState.InComplete -> COROUTINE_SUSPENDED
            CancelState.Cancelled -> throw CancellationException("Continuation is cancelled.")
            is CancelState.Complete<*> -> {
                (currentState as CancelState.Complete<T>).let {
                    it.exception?.let { throw it } ?: it.value
                }
            }
        }
    }

    //3.向Parent注册取消监听器
    private fun installCancelHandler() {
        if (isCompleted) return
        val parent = continuation.context[Job] ?: return
        parent.invokeOnCancel {
            doCancel()
        }
    }

    //当自己被取消时取消Parent，Parent被取消后触发自己被取消的逻辑
    fun cancel() {
        if (isCompleted) return
        val parent = continuation.context[Job] ?: return
        parent.cancel()
    }

    //4.向自己注册取消监听器
    fun invokeOnCancellation(onCancel: OnCancel) {
        val newState = state.updateAndGet { prev ->
            when (prev) {
                CancelState.InComplete -> CancelState.CancelHandler(onCancel)
                is CancelState.CancelHandler -> throw IllegalStateException("It's prohibited to register multiple handlers.")
                is CancelState.Complete<*>,
                CancelState.Cancelled -> prev
            }
        }
        if (newState is CancelState.Cancelled) {
            onCancel()
        }
    }

    //5.当Parent被取消时，触发在自己身上注册的取消监听器。
    private fun doCancel() {
        val prevState = state.getAndUpdate { prev ->
            when (prev) {
                is CancelState.CancelHandler,
                CancelState.InComplete -> {
                    CancelState.Cancelled
                }
                CancelState.Cancelled,
                is CancelState.Complete<*> -> {
                    prev
                }
            }
        }
        if (prevState is CancelState.CancelHandler) {

            //回调取消监听器
            prevState.onCancel()

            //向上游Continuation抛一个取消异常
            resumeWithException(CancellationException("Cancelled."))
        }
    }
}

//支持取消的挂起函数，利用CancellableContinuation实现协程的恢复执行和取消机制。
suspend inline fun <T> suspendCancellableCoroutine(
        crossinline block: (CancellableContinuation<T>) -> Unit
): T = suspendCoroutineUninterceptedOrReturn { continuation ->
    val cancellable = CancellableContinuation(continuation.intercepted())
    block(cancellable)
    cancellable.getResult()
}