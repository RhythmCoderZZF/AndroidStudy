package com.example.android_study.android.notification

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_rv.*

class NotificationMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1. 基本使用", NotificationActivity::class.java, ""),
            )
        )
    }
}