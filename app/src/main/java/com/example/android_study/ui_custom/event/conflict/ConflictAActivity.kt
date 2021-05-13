package com.example.android_study.ui_custom.event.conflict

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class ConflictAActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_dispatch_conflict_a

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}