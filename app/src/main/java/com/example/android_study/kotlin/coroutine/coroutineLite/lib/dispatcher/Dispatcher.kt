package com.bennyhuo.kotlin.coroutines.dispatcher

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor

interface Dispatcher {
    fun dispatch(block: ()->Unit)
}

//拦截器。拦截上游Continuation，生成新的Continuation
open class DispatcherContext(private val dispatcher: Dispatcher) : AbstractCoroutineContextElement(
    ContinuationInterceptor
), ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T>
            = DispatchedContinuation(continuation, dispatcher)
}

//上游Continuation的套壳，类似CancellationContinuation。用于在恢复上游协程的执行过程切换线程，具体的实现方式由不同的Dispatcher子类来实现。
private class DispatchedContinuation<T>(val delegate: Continuation<T>, val dispatcher: Dispatcher) : Continuation<T>{
    override val context = delegate.context

    override fun resumeWith(result: Result<T>) {
        dispatcher.dispatch {
            delegate.resumeWith(result)
        }
    }
}