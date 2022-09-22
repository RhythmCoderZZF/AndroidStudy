package com.example.android_study.performance.power

import android.os.Bundle
import android.os.PowerManager
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.webview.display_vue_project.WebViewAy
import com.example.android_study.android.webview.interaction.WebViewInteractionMainActivity
import com.example.android_study.performance.power.wakelock.WakelockAy
import kotlinx.android.synthetic.main.activity_fragment_main.*

class PerformancePowerMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.wakelock sample", WakelockAy::class.java,"使用WakeLock基础案例"),
        ))
    }
}