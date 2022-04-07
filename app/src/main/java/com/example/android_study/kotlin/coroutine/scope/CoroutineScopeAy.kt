package com.example.android_study.kotlin.coroutine.scope

import android.os.Bundle
import com.bennyhuo.kotlin.coroutines.Job
import com.bennyhuo.kotlin.coroutines.delay
import com.bennyhuo.kotlin.coroutines.dispatcher.Dispatchers
import com.bennyhuo.kotlin.coroutines.exception.CoroutineExceptionHandler
import com.bennyhuo.kotlin.coroutines.launch
import com.bennyhuo.kotlin.coroutines.scope.GlobalScope
import com.bennyhuo.kotlin.coroutines.scope.coroutineScope
import com.bennyhuo.kotlin.coroutines.scope.supervisorScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_coroutine_scope.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import kotlin.coroutines.suspendCoroutine

/**
 * sample of cancel coroutine
 */
class CoroutineScopeAy : BaseActivity() {
    private var job: Job? = null
    private var kJob: kotlinx.coroutines.Job? = null
    override fun getLayoutId() = R.layout.activity_coroutine_scope

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        //1.测试scopeContext[Job]实例的变化
        part1()

        //2.测试coroutineScope
        part2()

        //3.测试supervisorScope
        part3()
        launch {
            kotlinx.coroutines.coroutineScope {
                suspendCoroutine {  }
            }
        }

    }

    private fun part1() {
        btn.setOnClickListener {
            job = GlobalScope.launch(Dispatchers.Android) {
                CmdUtil.v("1-${scopeContext[Job]}")
                launch(Dispatchers.Android) {
                    delay(1000)
                    CmdUtil.v("2-${scopeContext[Job]}")
                    CmdUtil.v("Scope Job is Active:${job?.isActive}")
                }
                CmdUtil.v("3-${scopeContext[Job]}")
            }
        }
    }

    private fun part2() {
        btn1.setOnClickListener {
            GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                CmdUtil.e("1异常处理器捕获:${coroutineContext[Job]} —— ${throwable.message}")
            }) {
                CmdUtil.v("父协程启动...")
                coroutineScope {
                    launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                        CmdUtil.e("2异常处理器捕获:${coroutineContext[Job]} —— ${throwable.message}")
                    }) {
                        CmdUtil.v("Child[1]启动...")
                        delay(1000)
                        throw RuntimeException("CRASH!")
                    }
                    launch {
                        CmdUtil.v("Child[2]启动...")
                        delay(1500)
                        CmdUtil.i("Child[2]结束!")
                    }
                }
                CmdUtil.v("父协程结束")
            }
        }
    }


    private fun part3() {
        btn2.setOnClickListener {
            GlobalScope.launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                CmdUtil.e("1异常处理器捕获:${coroutineContext[Job]} —— ${throwable.message}")
            }) {
                CmdUtil.v("父协程启动...")
                supervisorScope {
                    launch(CoroutineExceptionHandler { coroutineContext, throwable ->
                        CmdUtil.e("2异常处理器捕获:${coroutineContext[Job]} —— ${throwable.message}")
                    }) {
                        CmdUtil.v("Child[1]启动...")
                        delay(1000)
                        throw RuntimeException("CRASH!")
                    }
                    launch {
                        CmdUtil.v("Child[2]启动...")
                        delay(1500)
                        CmdUtil.i("Child[2]结束!")
                    }
                }
                CmdUtil.v("父协程结束")
            }
        }
    }


//    private fun part3() {
//        btn1.setOnClickListener {
//            job = GlobalScope.launch(Dispatchers.Android) {
//                CmdUtil.v("1-${scopeContext[Job]}")
//                launch(Dispatchers.Android) {
//                    delay(1000)
//                    CmdUtil.v("2-${scopeContext[Job]}")
//                    CmdUtil.v("Scope Job is Active:${job?.isActive}")
//                }
//                CmdUtil.v("3-${scopeContext[Job]}")
//            }
//        }
//
//
//        btn1.setOnClickListener {
//            kJob = launch {
//                CmdUtil.v("1-${coroutineContext[kotlinx.coroutines.Job]}")
//                launch {
//                    delay(1000)
//                    CmdUtil.v("2-${coroutineContext[kotlinx.coroutines.Job]}")
//                    CmdUtil.v("Scope Job is Active:${kJob?.isActive}")
//                }
//                CmdUtil.v("3-${coroutineContext[kotlinx.coroutines.Job]}")
//            }
//
//            launch {
//                repeat(15) {
//                    CmdUtil.i("##${kJob?.isActive}")
//                    delay(100)
//                }
//            }
//        }
//    }
}