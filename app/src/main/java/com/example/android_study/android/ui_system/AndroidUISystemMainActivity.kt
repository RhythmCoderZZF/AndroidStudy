package com.example.android_study.android.ui_system

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.ui_system.window.UiSystemWindowActivity
import kotlinx.android.synthetic.main.activity_rv.*

class AndroidUISystemMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(rv, listOf(
                Entry("1.Window", UiSystemWindowActivity::class.java,"创建一个Window"),
        ))
    }
}