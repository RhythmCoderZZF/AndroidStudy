package com.example.android_study.android.activity.launchMode

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.activity.launchMode.singleInstance.SingleInstanceActivity
import com.example.android_study.android.activity.launchMode.singleTask.SingleTaskActivity
import com.example.android_study.android.activity.launchMode.singleTop.SingleTopActivity
import kotlinx.android.synthetic.main.activity_launch_mode.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class LaunchModeActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_launch_mode

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn_single_task.setOnClickListener {
            startActivity(Intent(this, SingleTaskActivity::class.java))
        }
        btn_single_instance.setOnClickListener {
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }
        btn_single_top.setOnClickListener {
            startActivity(Intent(this, SingleTopActivity::class.java))
        }

    }
}