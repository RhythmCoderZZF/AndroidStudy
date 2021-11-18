package com.example.android_study.ui_custom.demo.demo_map

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_ui_custom_path_measure_map.*

class UIPathMeasureDemoMapActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_ui_custom_path_measure_map

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn_start.setOnClickListener {
            v_demo_map.segment()
        }
    }
}