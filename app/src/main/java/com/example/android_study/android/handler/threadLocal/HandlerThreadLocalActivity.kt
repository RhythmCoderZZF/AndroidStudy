package com.example.android_study.android.handler.threadLocal

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HandlerThreadLocalActivity : BaseActivity(), Runnable {
    private val threadLocal = ThreadLocal<String>()
    private val threadLocal1 = ThreadLocal<String>()

    override fun getLayoutId() = R.layout.activity_handler_thread_loacal

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)

    }

    override fun run() {
        threadLocal.set("1")
        threadLocal1.set("11")
        CmdUtil.v("thread name:${Thread.currentThread().name}")
        CmdUtil.i("threadLocal value:${threadLocal.get()} threadLocal1 value:${threadLocal1.get()}")

        launch(IO) {
            delay(1000)
            CmdUtil.e("thread name:${Thread.currentThread().name} ")
            CmdUtil.i("threadLocal value:${threadLocal.get()} threadLocal1 value:${threadLocal1.get()}")
            threadLocal.set("1")
            threadLocal1.set("11")
            CmdUtil.e("thread name:${Thread.currentThread().name}")
            CmdUtil.i("threadLocal value:${threadLocal.get()} threadLocal1 value:${threadLocal1.get()}")
        }
    }
}