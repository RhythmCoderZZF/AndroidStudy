package com.bennyhuo.kotlin.coroutines.core

import com.bennyhuo.kotlin.coroutines.Job
import com.bennyhuo.kotlin.coroutines.OnCancel

typealias OnCompleteT<T> = (Result<T>) -> Unit

interface Disposable {

    //监听器在执行完毕后，对自己进行清除
    fun dispose()
}

//封装协程完成监听器的Disposable
class CompletionHandlerDisposable<T>(val job: Job, val onComplete: OnCompleteT<T>) : Disposable {
    override fun dispose() {
        job.remove(this)
    }
}

//封装协程取消监听器Disposable
class CancellationHandlerDisposable(val job: Job, val onCancel: OnCancel) : Disposable {
    override fun dispose() {
        job.remove(this)
    }
}