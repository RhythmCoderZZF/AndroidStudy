package com.example.android_study._kotlin._coroutines._1_base

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import com.example.android_study.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity__1__ay.*
import kotlinx.coroutines.*

class _1_Ay : BaseActivity(), Runnable {
    override fun getLayoutId() = R.layout.activity__1__ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this, "基础")
        CmdUtil.showWindow()
        window.decorView.postDelayed(this, 200)

    }

    override fun run() {
        //1. 启动 UI 协程与取消
        var job: Job? = null
        btn0.setOnClickListener {
            job = GlobalScope.launch(Dispatchers.Main) {
                for (i in 0..20) {
                    tex0.text = "$i"
                    //此处的delay只会挂起协程，不会阻塞主线程相当于（View.postDelayed）
                    delay(200)
                }

                launch {
                    for (i in 0..20) {
                        tex0.append("!")
                        delay(200)
                    }
                }

                launch {
                    for (i in 0..20) {
                        tex0.append("^")
                        delay(200)
                    }
                }
            }
            CmdUtil.v("非阻塞主线程")
        }
        btn0_1.setOnClickListener {
            job?.cancel()
        }
    }

}