package com.example.android_study.kotlin.coroutine.base.understandCoroutine

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_understand.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.*


class CoroutineUnderstandActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_understand
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        part1()
    }

    private fun part1() {
        btn.setOnClickListener {
            CmdUtil.v("1")
            checkOut {
                CmdUtil.v("2")
            }
        }

        btn1.setOnClickListener {
            launch {
                CmdUtil.v("1")
                checkOut1()
                CmdUtil.v("2")
            }
        }
    }

    private fun checkOut(callback: () -> Unit) {
        thread {
            Thread.sleep(1000)
            callback()
        }
    }

    private suspend fun checkOut1() = suspendCoroutine<Unit> {
        thread {
            Thread.sleep(1000)
            it.resume(Unit)
        }
    }
}