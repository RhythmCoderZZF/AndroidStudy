package com.example.android_study.Java.Juc.wait_notify

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_juc_join.*
import okhttp3.internal.notifyAll
import okhttp3.internal.wait
import kotlin.concurrent.thread

class JucWaitNotifyActivity : BaseActivity() {
    lateinit var t0: Thread
    lateinit var t1: Thread

    override fun getLayoutId() = R.layout.activity_juc_wait_notify

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        t0 = thread(name = "线程1") {
            synchronized(this) {
                CmdUtil.v("${Thread.currentThread().name}:进入同步代码块")
                CmdUtil.v("${Thread.currentThread().name}:wait...")
                this.wait()
                CmdUtil.v("${Thread.currentThread().name}:重新执行")
            }
        }
        Thread.sleep(200)
        t1 = thread(name = "线程2") {
            synchronized(this) {
                CmdUtil.i("${Thread.currentThread().name}:进入同步代码块")
                CmdUtil.i("${Thread.currentThread().name}:notifyAll!")
                this.notifyAll()
                CmdUtil.i("${Thread.currentThread().name}:重新执行，开始休眠...")
                Thread.sleep(2000)

            }
        }


    }
}