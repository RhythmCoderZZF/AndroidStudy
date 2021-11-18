package com.example.android_study.android.activity

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.activity.fragment.FragmentMainActivity
import com.example.android_study.android.activity.launchMode.LaunchModeActivity
import com.example.android_study.android.activity.lifecycle.LifecycleActivity
import com.example.android_study.android.activity.result_api.ActivityResultApiReceiveAy
import com.example.android_study.android.data_and_file._ContentProvider.AndroidContentProviderMainActivity
import com.example.android_study.android.data_and_file.internal_and_outspace.AndroidDataAndFileIOSpaceActivity
import com.example.android_study.android.drawable_and_graph.drawable.AndroidDrawableActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(rv, listOf(
                Entry("1.生命周期", LifecycleActivity::class.java),
                Entry("2.启动模式", LaunchModeActivity::class.java),
                Entry("3.Fragment", FragmentMainActivity::class.java),
                Entry("4.Result Api", ActivityResultApiReceiveAy::class.java),
        ))

    }
}