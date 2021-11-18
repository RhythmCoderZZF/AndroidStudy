package com.example.android_study.kotlin._2_coroutine.coroutineContext

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_context.*
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import kotlin.concurrent.thread
import kotlin.coroutines.*

@ExperimentalStdlibApi
class CoroutineContextActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_context
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()


        btn.setOnClickListener {
            var context = Dispatchers.IO + Job() + CoroutineName("名字")
            CmdUtil.v("$context")
            CmdUtil.v("${context[CoroutineName]}")
            context = context.minusKey(Job)
            CmdUtil.v("minusKey:$context")
        }

        btn1.setOnClickListener {
            launch(MyDispatcher) {
                CmdUtil.i(">${Thread.currentThread().name}")
                repeat(3) {
                    val value = foo(it)
                    CmdUtil.e(">${Thread.currentThread().name}")
                    CmdUtil.v(value)
                }
            }
        }
        runBlocking {  }
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