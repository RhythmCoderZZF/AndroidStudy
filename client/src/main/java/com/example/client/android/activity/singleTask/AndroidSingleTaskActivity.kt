package com.example.client.android.activity.singleTask

import android.content.Intent
import android.os.Bundle
import com.example.client.R
import com.example.client._base.BaseActivity
import com.example.client._base.LogUtil
import com.example.client.android.activity.standerd.AndroidStandardActivity
import kotlinx.android.synthetic.main.activity_launch_mode_single_task.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class AndroidSingleTaskActivity : BaseActivity() {
    private val TAG=javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_mode_single_task)
        btn_start.setOnClickListener {
            startActivity(Intent(this, AndroidStandardActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        LogUtil.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtil.d(TAG,"onResume")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtil.d(TAG,"onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy")
    }
}