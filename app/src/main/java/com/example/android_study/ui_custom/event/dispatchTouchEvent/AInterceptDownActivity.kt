package com.example.android_study.ui_custom.event.dispatchTouchEvent

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_cus_event_dispatch_b_not_consume.*

class AInterceptDownActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_dispatch_b_not_consume

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        A.interceptDown=true
    }
}