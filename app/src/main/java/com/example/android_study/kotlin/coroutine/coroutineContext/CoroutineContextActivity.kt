package com.example.android_study.kotlin.coroutine.coroutineContext

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_context.*
import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.util.concurrent.Executors
import kotlin.coroutines.*

@ExperimentalStdlibApi
class CoroutineContextActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_context
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        //——————————————————《上下文加减操作》————————————————————
        btn.setOnClickListener {
            var context = Dispatchers.IO + Job() + CoroutineName("名字")
            CmdUtil.v("$context")
            CmdUtil.i("${context[CoroutineName]}")
            context = context.minusKey(Job)
            CmdUtil.e("minusKey:$context")
        }
        //——————————————————《协程上下文元素[CoroutineName、CoroutineExceptionHandler]》————————————————————
        btn1.setOnClickListener {
            suspend {
                CmdUtil.i("1当前线程" + Thread.currentThread().name)
                delay(1000)
                CmdUtil.i("2当前线程" + Thread.currentThread().name)
                throw ArithmeticException("><！")
                1
            }.startCoroutine(object : Continuation<Int> {
                override val context: CoroutineContext
                    get() = CoroutineName("主协程") + CoroutineExceptionHandler { _, t ->
                        CmdUtil.e("4异常:${t.message}")
                    }

                override fun resumeWith(result: Result<Int>) {
                    result.onFailure {
                        CmdUtil.e("3异常:${it.message}")
                        context[CoroutineExceptionHandler]?.handleException(context, it)
                    }
                    result.onSuccess {
                        CmdUtil.i("SUCCESS!!!")
                    }
                }
            })
        }

        //——————————————————《协程上下文元素[CoroutineDispatcher]》————————————————————
        btn2.setOnClickListener {
            launch(MyDispatcher) {
                CmdUtil.i(">${Thread.currentThread().name}")
                repeat(3) {
                    val value = foo(it)
                    CmdUtil.e(">${Thread.currentThread().name}")
                    CmdUtil.v(value)
                }
            }
        }
        //——————————————————《协程上下文元素[Job]》————————————————————
        runBlocking { }
    }

    private object MyDispatcher : CoroutineDispatcher() {
        private val executor = Executors.newSingleThreadExecutor()
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            executor.execute(block)
        }
    }

    private suspend fun foo(i: Int) = suspendCoroutine<String> {
        window.decorView.postDelayed({
            it.resume("$i")
        }, 1000)
    }
}