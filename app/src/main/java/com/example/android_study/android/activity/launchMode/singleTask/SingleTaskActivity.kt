package com.example.android_study.android.activity.launchMode.singleTask

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_calendar_ay.*
import kotlinx.android.synthetic.main.activity_single_task.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class SingleTaskActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_single_task

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_start.setOnClickListener {
            startActivity(Intent().apply {
                action = "app.intent.test.singleTask"
            })
        }
        btn_start_1.setOnClickListener {
            startActivity(Intent(this,TaskAffinitySameActivity::class.java))
        }
        btn_start_2.setOnClickListener {
            startActivity(Intent(this,TaskAffinityDiffActivity::class.java))
        }
    }
}