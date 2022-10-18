package com.example.android_study.performance.anr

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_performance_anr.*
import kotlinx.android.synthetic.main.activity_performance_crash.*
import kotlin.concurrent.thread

/**
 * Author:create by RhythmCoder
 * Date:2021/7/28
 * Description:
 */
class PerformanceANRActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_performance_anr

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_block.setOnClickListener {
            Thread.sleep(20_000)
        }
    }
}