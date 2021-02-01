package com.example.android_study.dispatchEvent.interceptEvent

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil


class DispatchEventInterceptEventActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_dispatch_event_intercept_event

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

    }
}
