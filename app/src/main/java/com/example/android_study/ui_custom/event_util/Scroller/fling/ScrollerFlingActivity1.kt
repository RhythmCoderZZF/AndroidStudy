package com.example.android_study.ui_custom.event_util.Scroller.fling

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil


class ScrollerFlingActivity1 : BaseActivity() {
    private var mDataCount = 0

    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_scroller_fling1

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}