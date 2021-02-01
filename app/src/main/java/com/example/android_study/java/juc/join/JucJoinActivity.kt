package com.example.android_study.java.juc.join

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_juc_join.*
import kotlin.concurrent.thread

class JucJoinActivity : BaseActivity() {
    lateinit var t0: Thread
    lateinit var t1: Thread

    override fun getLayoutId() = R.layout.activity_juc_join

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        //1. 同步等待——————————————————————————————————————————————


        button.setOnClickListener {
            t0 = thread(start = false, name = "A") {
                runOnUiThread { CmdUtil.v("A start...") }
                Thread.sleep(2000)
                runOnUiThread { CmdUtil.v("A finish...") }
            }
            t1 = thread(start = false, name = "B") {
                runOnUiThread { CmdUtil.v("B start...") }
                try {
                    t0.join()
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    runOnUiThread { CmdUtil.e("B interrupt! ${Thread.currentThread().isInterrupted}") }
                }
                runOnUiThread { CmdUtil.v("B finish...") }
            }
            t0.start()
            t1.start()
        }
        button1.setOnClickListener {
            t1.interrupt()
        }

        //1. 限时同步等待——————————————————————————————————————————————
        button2.setOnClickListener {
            t0 = thread(start = false, name = "A") {
                runOnUiThread { CmdUtil.v("A start...") }
                Thread.sleep(2000)
                runOnUiThread { CmdUtil.v("A finish...") }
            }
            t1 = thread(start = false, name = "B") {
                runOnUiThread { CmdUtil.v("B start...") }
                try {
                    t0.join(1000)
                } catch (e: InterruptedException) {
                    Thread.currentThread().interrupt()
                    runOnUiThread { CmdUtil.e("B interrupt! ${Thread.currentThread().isInterrupted}") }
                }
                runOnUiThread { CmdUtil.v("B finish...") }
            }
            t0.start()
            t1.start()
        }
        button3.setOnClickListener {
            t1.interrupt()
        }

    }
}