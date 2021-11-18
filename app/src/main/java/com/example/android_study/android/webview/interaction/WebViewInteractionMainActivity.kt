package com.example.android_study.android.webview.interaction

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.webview.interaction.android_call_js.AndroidCallJsFragment
import com.example.android_study.android.webview.interaction.js_call_android.JsCallAndroidFragment

class WebViewInteractionMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
       setViewPagerFragment(window, listOf(
           EntryF("1.Android调用Js", AndroidCallJsFragment()),
           EntryF("2.Js调用Android", JsCallAndroidFragment())
       ))

    }
}