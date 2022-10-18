package com.example.android_study.android.framework.power

import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_framework_power_api.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PowerAPIActivity : BaseActivity() {
    private lateinit var mPowerManager:PowerManager

    override fun getLayoutId() = R.layout.activity_android_framework_power_api

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        mPowerManager =getSystemService(Context.POWER_SERVICE) as PowerManager
        btn_nap.setOnClickListener {
        }
    }
}