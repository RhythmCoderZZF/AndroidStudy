package com.example.android_study.kotlin.coroutine_core

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.framework._network.retrofit.HomeData
import com.example.android_study.framework._network.retrofit.DataManager
import kotlinx.android.synthetic.main.activity_coroutine_core.*
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * Coroutine 原理剖析
 */
class CoroutineCoreActivity : BaseActivity() {
    var list: List<HomeData>? = null
    override fun getLayoutId() = R.layout.activity_coroutine_core

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()


        download.setOnClickListener {
            startCoroutine {
                CmdUtil.v("startCoroutine block()执行!")
                withContextIO {
                    CmdUtil.v("withContextIO block start:耗时操作开始...")
                    val response = DataManager.getJson().execute()
                    list = response.body()?.data
                    CmdUtil.v("withContextIO block end:耗时操作结束!")
                }
                showToast(list.toString())
            }
        }
    }

    /**
     * 自定义UIContinuationWrapper
     * ——和拦截器组合使用，用于篡改原continuation逻辑
     */
    inner class UIContinuationWrapper<T>(private val continuation: Continuation<T>) : Continuation<T> {
        init {
            CmdUtil.v("4.初始化UIContinuationWrapper")
        }

        override val context: CoroutineContext
            get() = continuation.context

        override fun resumeWith(result: Result<T>) {
            CmdUtil.e("5.UIContinuationWrapper.resumeWith()")
            runOnUiThread {
                CmdUtil.e("6.${continuation.javaClass.name}.resumeWith()")
                continuation.resumeWith(result)
            }
        }
    }

    /**
     * 拦截器
     * ——生成一个新的continuation来包装原continuation
     */
    inner class AsyncContext : AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {
        init {
            CmdUtil.v("1.初始化AsyncContext拦截器")
            Dispatchers
        }

        override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
            CmdUtil.v("3.AsyncContext拦截器interceptContinuation()")
            return UIContinuationWrapper(continuation.context.fold(continuation) { continuation, element ->
                if (element != this && element is ContinuationInterceptor) {
                    element.interceptContinuation(continuation)
                } else {
//                    CmdUtil.i("element:${element.javaClass.simpleName}")
                    continuation
                }
            })
        }
    }

    /**
     * BaseContinuation(Completion)最后resumeWith()的Continuation
     */
    class ContextContinuation(override val context: CoroutineContext = EmptyCoroutineContext) : Continuation<Unit> {
        companion object {
            val name = "ContextContinuation"
        }

        init {
            CmdUtil.v("2.初始化BaseContinuation")
        }

        override fun resumeWith(result: Result<Unit>) {
            CmdUtil.v("7.ContextContinuation.resumeWith()")
        }
    }

    /**
     * 1. 创建启动协程
     */
    private fun startCoroutine(block: suspend () -> Unit) {
        CmdUtil.v("startCoroutine()执行..")
        block.startCoroutine(ContextContinuation(AsyncContext()))
    }


    /**
     * 2. 切换IO线程
     */
    private suspend fun <T> withContextIO(block: () -> T) = suspendCoroutine<T> { continuation ->
        CmdUtil.v("withContextIO() start 此时的continuation:${continuation.javaClass.simpleName}")
        thread {
            try {
                continuation.resumeWith(Result.success(block()))
            } catch (e: Exception) {
                continuation.resumeWith(Result.failure(e))
            }
        }
    }
}