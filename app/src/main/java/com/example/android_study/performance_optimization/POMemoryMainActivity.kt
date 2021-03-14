package com.example.android_study.performance_optimization

import android.app.ActivityManager
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class POMemoryMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val ams=getSystemService(ACTIVITY_SERVICE) as ActivityManager

        window.decorView.post {
            CmdUtil.v("app内存:${ams.memoryClass}")
            setViewPagerFragment(window, listOf(
//                    RectF("1.")
            ))
        }

    }
}