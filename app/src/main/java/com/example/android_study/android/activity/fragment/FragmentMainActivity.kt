package com.example.android_study.android.activity.fragment

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.activity.fragment.communication.FragmentCommunicationAy
import com.example.android_study.android.activity.fragment.create.FragmentCreateAy
import com.example.android_study.android.activity.fragment.fragmentManager.FragmentManagerActivity
import com.example.android_study.android.activity.fragment.lifecycle.FragmentLifecycleAy
import com.example.android_study.android.activity.fragment.withAnimation.FragmentWithAnimationActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class FragmentMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_fragment_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.2种创建方式", FragmentCreateAy::class.java),
                Entry("2.创建管理fragment", FragmentManagerActivity::class.java),
                Entry("3.Fragment过场动画", FragmentWithAnimationActivity::class.java),
                Entry("4.生命周期", FragmentLifecycleAy::class.java),
                Entry("5.与Fragment通信", FragmentCommunicationAy::class.java),
            )
        )

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}