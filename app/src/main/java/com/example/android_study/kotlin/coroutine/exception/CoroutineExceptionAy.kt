package com.example.android_study.kotlin.coroutine.exception

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bennyhuo.kotlin.coroutines.launch
import com.bennyhuo.kotlin.coroutines.utils.log
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_coroutine_exception_ay.*
import kotlinx.coroutines.*
import java.lang.RuntimeException

/**
 * sample of cancel coroutine
 */
class CoroutineExceptionAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_coroutine_exception_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /* 协程内部异常 */
        part1()
        /* 挂起函数被取消 */
        part2()
        /* 父协程异常传播 */
        part3()
        /* 子协程异常传播 */
        part4()
    }


    private fun part1() {
        val handler = CoroutineExceptionHandler { _, t ->
            CmdUtil.e("err:${t.message}")
        }
        btn.setOnClickListener {
            launch(handler) {
                CmdUtil.v("1")
                throw RuntimeException("><!")
                CmdUtil.v("2")
            }
        }
    }

    private fun part2() {
        val handler = CoroutineExceptionHandler { _, t ->
            CmdUtil.e("err:${t.message}")
        }
        btn1.setOnClickListener {
            val job = launch(handler) {
                CmdUtil.v("1")
                myDelay(1000)
                CmdUtil.v("2")
            }
            launch {
                delay(500)
                job.cancel()
            }
        }
    }

    private suspend fun myDelay(delay: Long) = suspendCancellableCoroutine<Unit> {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ it.resumeWith(Result.success(Unit)) }, delay)
        it.invokeOnCancellation {
            handler.removeCallbacksAndMessages(null)
            CmdUtil.i("suspend fun cancelled")
        }
    }


    private fun part3() {
        btn2.setOnClickListener {
            val handler = CoroutineExceptionHandler { _, t ->
                CmdUtil.e("err:${t.message}")
            }
            val handler1 =
                com.bennyhuo.kotlin.coroutines.exception.CoroutineExceptionHandler { _, t ->
                    CmdUtil.e("err:${t.message}")
                }
            launch(handler) {
                launch {
                    CmdUtil.v("1")
                    supervisorScope {  }
                    coroutineScope {  }
                    launch {
                        delay(1000)
                        CmdUtil.v("2")
                    }
                    delay(100)
                    log("debug")
                    throw RuntimeException("><!")
                }
            }
//
//
//            com.bennyhuo.kotlin.coroutines.scope.GlobalScope.launch(handler1) {
//                CmdUtil.v("111")
//                launch {
//                    com.bennyhuo.kotlin.coroutines.delay(1000)
//                    CmdUtil.v("222")
//                }
//                com.bennyhuo.kotlin.coroutines.delay(100)
//                throw RuntimeException("1><!")
//            }
        }
    }

    private fun part4() {
        val handler = CoroutineExceptionHandler { _, t ->
            CmdUtil.e("err:${t.message}")
        }
        btn3.setOnClickListener {
            launch(handler) {
                CmdUtil.v("1")
                launch {
                    throw RuntimeException("><!")
                }
                delay(1000)
                CmdUtil.v("2")
            }
        }
    }
}