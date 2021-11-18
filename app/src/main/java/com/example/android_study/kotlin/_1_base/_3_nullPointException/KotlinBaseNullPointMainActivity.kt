package com.example.android_study.kotlin._1_base._3_nullPointException

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class KotlinBaseNullPointMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_base_null_point_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post {
            val name = Person().name
            try {
                name.length
            } catch (e: NullPointerException) {
                CmdUtil.e("$e")
            }
        }
    }
}