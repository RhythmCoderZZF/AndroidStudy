package com.example.android_study.ui_custom.study._4_anim.viewGroup_anim

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._4_anim.animator.*

class _UIViewGroupAnimMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1. android:animatelayoutChanges", UILayoutChangeAnimFragment()),
                EntryF("2. LayoutTransition", UILayoutTransitionFragment()),
        ))
    }
}