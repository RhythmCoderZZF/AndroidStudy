package com.example.android_study.android.framework.power

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.webview.display_vue_project.WebViewAy
import com.example.android_study.android.webview.interaction.WebViewInteractionMainActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidPowerManagerMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(rv, listOf(
                Entry("1.API", WebViewAy::class.java),
        ))

    }
}