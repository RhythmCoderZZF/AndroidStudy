package com.example.android_study.android.activity.launchMode.singleTask

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_launch_mode_task_affinity.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/20
 * Description:
 */
class TaskAffinitySameActivity : BaseActivity() {
    init {
        showLifecycle=true
    }
    override fun getLayoutId()= R.layout.activity_launch_mode_task_affinity

    override fun initViewAndData(savedInstanceState: Bundle?) {
        text.text="本Activity是SingleTask模式，且和app TaskAffinity相同"
        btn_start.setOnClickListener {
            startActivity(Intent(this, TaskAffinitySameActivity::class.java))
        }
    }
}