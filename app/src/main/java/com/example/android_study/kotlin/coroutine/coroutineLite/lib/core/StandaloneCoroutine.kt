package com.bennyhuo.kotlin.coroutines.core

import com.bennyhuo.kotlin.coroutines.exception.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class StandaloneCoroutine(context: CoroutineContext) : AbstractCoroutine<Unit>(context) {

    override fun handleJobException(e: Throwable): Boolean {

        //优先交给异常处理器处理，否子交给线程异常处理器处理（Android的默认处理是程序退出）
        context[CoroutineExceptionHandler]?.handleException(context, e) ?: Thread.currentThread()
            .let { it.uncaughtExceptionHandler.uncaughtException(it, e) }
        return true
    }
}