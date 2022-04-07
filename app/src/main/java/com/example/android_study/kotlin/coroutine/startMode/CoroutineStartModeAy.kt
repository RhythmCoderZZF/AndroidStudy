package com.example.android_study.kotlin.coroutine.startMode

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_coroutine_start_mode.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

/**
 * sample of cancel coroutine
 */
class CoroutineStartModeAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_coroutine_start_mode

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        //《启动模式》
        part1()
    }

    private fun part1() {
        btn_default.setOnClickListener {
            launch(start = CoroutineStart.DEFAULT) {
                CmdUtil.v("2")
            }
            CmdUtil.v("1")
        }
        btn_atomic.setOnClickListener {
            launch(start = CoroutineStart.ATOMIC) {
                CmdUtil.v("2")
            }
            CmdUtil.v("1")
        }
        btn_unDispatched.setOnClickListener {
            launch(start = CoroutineStart.UNDISPATCHED) {
                CmdUtil.v("2")
            }
            CmdUtil.v("1")
        }
        btn_lazy.setOnClickListener {
            launch(start = CoroutineStart.DEFAULT) {
                CmdUtil.v("2")
            }
            CmdUtil.v("1")
        }
    }

}