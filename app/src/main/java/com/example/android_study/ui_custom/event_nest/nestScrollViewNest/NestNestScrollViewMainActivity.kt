package com.example.android_study.ui_custom.event_nest.nestScrollViewNest

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class NestNestScrollViewMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_cus_event_nest_nest_scroll_view_nest

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}