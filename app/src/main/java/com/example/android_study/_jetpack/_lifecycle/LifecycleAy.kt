package com.example.android_study._jetpack._lifecycle

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import com.example.android_study.util.ToolbarHelper

class LifecycleAy : BaseActivity() {
    private var observer = MyObserver(lifecycle)

    init {
        showLifecycle = true
        lifecycle.addObserver(observer)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_lifecycle_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this,"Lifecycle")
        CmdUtil.showWindow()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer.getCurrLifeState()
    }

    override fun onStart() {
        super.onStart()
        observer.getCurrLifeState()
    }

    override fun onResume() {
        super.onResume()
        observer.getCurrLifeState()
    }


    override fun onPause() {
        super.onPause()
        observer.getCurrLifeState()
    }

    override fun onStop() {
        super.onStop()
        observer.getCurrLifeState()
    }

    override fun onDestroy() {
        super.onDestroy()
        observer.getCurrLifeState()
    }
}