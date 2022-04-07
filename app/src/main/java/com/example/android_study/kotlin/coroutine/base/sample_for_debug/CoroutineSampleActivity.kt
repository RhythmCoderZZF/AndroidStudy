package com.example.android_study.kotlin.coroutine.base.sample_for_debug

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_sample.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.*


class CoroutineSampleActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_sample
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn.setOnClickListener {
            suspend {
                CmdUtil.v(foo())
                CmdUtil.v(bar())
                throw java.lang.IllegalArgumentException("")
            }.startCoroutine(object : Continuation<Unit> {
                override val context: CoroutineContext
                    get() = EmptyCoroutineContext

                override fun resumeWith(result: Result<Unit>) {
                    CmdUtil.v("completion resume:$result")
                }
            })
        }
    }

    suspend fun foo() = suspendCoroutine<String> {
        thread {
            Thread.sleep(1000)
            it.resume("Hello")
        }
    }

    suspend fun bar() = suspendCancellableCoroutine<String> {
        it.resume("World")
    }
}