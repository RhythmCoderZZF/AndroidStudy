package com.bennyhuo.kotlin.coroutines.scope

import com.bennyhuo.kotlin.coroutines.Job
import com.bennyhuo.kotlin.coroutines.core.AbstractCoroutine
import kotlin.coroutines.*

interface CoroutineScope {

    //保存所有子协程的上下文
    val scopeContext: CoroutineContext
}

internal class ContextScope(context: CoroutineContext) : CoroutineScope {
    override val scopeContext: CoroutineContext = context
}

operator fun CoroutineScope.plus(context: CoroutineContext): CoroutineScope =
    ContextScope(scopeContext + context)

//取消当前所处的协程
fun CoroutineScope.cancel() {
    val job = scopeContext[Job]
        ?: error("Scope cannot be cancelled because it does not have a job: $this")
    job.cancel()
}

//协同作用域。
suspend fun <R> coroutineScope(block: suspend CoroutineScope.() -> R): R =
    suspendCoroutine { continuation ->
        val coroutine = ScopeCoroutine(continuation.context, continuation)
        block.startCoroutine(coroutine, coroutine)
    }

internal open class ScopeCoroutine<T>(
    context: CoroutineContext,
    protected val continuation: Continuation<T>
) : AbstractCoroutine<T>(context) {

    override fun resumeWith(result: Result<T>) {
        super.resumeWith(result)
        continuation.resumeWith(result)
    }
}

