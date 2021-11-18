package com.example.client.android.activity

import android.os.Bundle
import com.example.client.R
import com.example.client._base.BaseActivity
import com.example.client._base.Entry
import com.example.client._base.LogUtil
import com.example.client.android.activity.singleInstance.AndroidSingleInstanceActivity
import com.example.client.android.activity.singleTask.AndroidSingleTaskActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/19
 * Description:
 */
class AndroidActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.d("","onCreate")
        setContentView(R.layout.activity_main)
        setRecyclerView(
            rv, listOf(
                Entry("1.SingleTask", AndroidSingleTaskActivity::class.java),
                Entry("2.SingleInstance", AndroidSingleInstanceActivity::class.java),
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d("","onDestroy")
    }
}