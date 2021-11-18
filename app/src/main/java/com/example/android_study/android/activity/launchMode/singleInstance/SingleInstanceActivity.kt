package com.example.android_study.android.activity.launchMode.singleInstance

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_single_instance.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class SingleInstanceActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_single_instance

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_start.setOnClickListener {
            startActivity(Intent().apply {
                action = "app.intent.test.singleInstance"
            })
        }
    }
}