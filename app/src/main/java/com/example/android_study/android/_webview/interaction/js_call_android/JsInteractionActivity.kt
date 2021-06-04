package com.example.android_study.android._webview.interaction.js_call_android

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_android_webview_interaction_js_call.*

class JsInteractionActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_android_webview_interaction_js_call
    override fun initViewAndData(savedInstanceState: Bundle?) {
        val str = intent.getStringExtra("jsCall")
        tv_js_call.text=str
    }
}