package com.example.android_study.kotlin._2_coroutine.sample

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_sample.*
import kotlin.concurrent.thread
import kotlinx.coroutines.*
import kotlin.coroutines.*


class CoroutineSampleActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_sample
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn.setOnClickListener {
            suspend {
                CmdUtil.i(Thread.currentThread().name)
                CmdUtil.v(foo())
                CmdUtil.i(Thread.currentThread().name)
                CmdUtil.v(bar())
            }.startCoroutine(object : Continuation<Unit> {
                override val context: CoroutineContext
                    get() = Dispatchers.Main

                override fun resumeWith(result: Result<Unit>) {
                    CmdUtil.v("completion resume!")
                }
            })
        }
    }

    suspend fun foo() = suspendCoroutine<String> {
        thread {
            Thread.sleep(10000)
            it.resume("Hello")
        }
    }

    suspend fun bar() = suspendCoroutine<String> {
        it.resume("World")
    }


//    suspend fun foo() {}
//
//    suspend fun bar(i: Int): String = "Hello"
//
//    suspend fun fizz(i: Int): String {
//        delay(800)
//        return "Hello"
//    }
}