package com.bennyhuo.kotlin.coroutines.core

import com.bennyhuo.kotlin.coroutines.Job

sealed class CoroutineState {

    //1.保存当前协程所有注册的监听器
    protected var disposableList: RecursiveList<Disposable> = RecursiveList.Nil
        private set

    //2.保存当前协程所有的直接子协程
    protected var children: RecursiveList<Job> = RecursiveList.Nil
        private set

    fun from(state: CoroutineState): CoroutineState {
        this.disposableList = state.disposableList
        this.children = state.children
        return this
    }

    fun with(element: Any): CoroutineState {
        when (element) {
            is Disposable -> this.disposableList = RecursiveList.Cons(element, this.disposableList)
            is Job -> this.children = RecursiveList.Cons(element, this.children)
        }
        return this
    }

    fun without(element: Any): CoroutineState {
        when (element) {
            is Disposable -> this.disposableList = this.disposableList.remove(element)
            is Job -> this.children = this.children.remove(element)
        }
        return this
    }

    //3.触发协程完成监听器
    fun <T> notifyCompletion(result: Result<T>) {
        this.disposableList.loopOn<CompletionHandlerDisposable<T>> {
            it.onComplete(result)
        }
    }

    //3.触发协程取消监听器
    fun notifyCancellation() {
        disposableList.loopOn<CancellationHandlerDisposable> {
            it.onCancel()
        }
    }

    fun clear() {
        this.disposableList = RecursiveList.Nil
        this.children = RecursiveList.Nil
    }

    //4.协程尝试进入“完成”状态，若该协程没有子协程，则转变为“完成”状态；否则转为“待完成”状态。
    fun <T> tryComplete(result: Result<T>): CoroutineState {
        return if (children == RecursiveList.Nil) Complete(
            result.getOrNull(),
            result.exceptionOrNull()
        ).from(this)
        else CompleteWaitForChildren(
            result.getOrNull(),
            result.exceptionOrNull(),
            this is Cancelling
        ).from(this)
    }

    override fun toString(): String {
        return "CoroutineState.${this.javaClass.simpleName}"
    }

    //状态1——运行
    class InComplete : CoroutineState()

    //状态2——被取消
    class Cancelling : CoroutineState()

    //状态3——待完成。
    class CompleteWaitForChildren<T>(
        val value: T? = null,
        val exception: Throwable? = null,
        val isCancelling: Boolean = false
    ) : CoroutineState() {
        fun copy(
            value: T? = this.value,
            exception: Throwable? = this.exception,
            isCancelling: Boolean = this.isCancelling
        ): CompleteWaitForChildren<T> {
            return CompleteWaitForChildren(
                value,
                exception,
                isCancelling
            ).from(this) as CompleteWaitForChildren<T>
        }

        //5.向所有直属子协程注入完成监听器。
        fun tryWaitForChildren(onChildComplete: (Job) -> Unit) {
            children.forEach { child ->
                child.invokeOnCompletion {
                    onChildComplete(child)
                }
            }
        }

        //6.当某一个子协程执行完毕，则将其从链表中移除，直到所有的子协程都执行完毕，将状态转为“完成”
        fun onChildCompleted(job: Job): CoroutineState {
            when (val currentChildren = children) {
                is RecursiveList.Cons -> {
                    if (currentChildren.tail == RecursiveList.Nil && currentChildren.head == job) {
                        return Complete(value, exception).from(this)
                    }
                }
            }

            //某一Child执行完毕，从链表中移除
            return CompleteWaitForChildren(value, exception, isCancelling).from(this).without(job)
        }
    }

    //状态4——已完成
    class Complete<T>(val value: T? = null, val exception: Throwable? = null) : CoroutineState()
}