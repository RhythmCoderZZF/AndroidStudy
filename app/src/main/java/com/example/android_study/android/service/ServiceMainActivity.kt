package com.example.android_study.android.service

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class ServiceMainActivity : BaseActivity() {


    override fun getLayoutId()=R.layout.activity_service_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}