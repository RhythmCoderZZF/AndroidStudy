package com.example.android_study.android.activity.launchMode.singleTop

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_single_top.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class SingleTopActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_single_top

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_start.setOnClickListener {
            startActivity(Intent(this, SingleTopActivity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        CmdUtil.v("onNewIntent")
    }
}