package com.example.android_study.ui_custom.study._4_anim.path_measure

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._4_anim.animator.*
import com.example.android_study.ui_custom.study._4_anim.viewGroup_anim.UILayoutChangeAnimFragment
import com.example.android_study.ui_custom.study._4_anim.viewGroup_anim.UILayoutTransitionFragment

class _UIPathMeasureMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1. 基础", UIPathMeasureFragment()),
                EntryF("2. getSegment()函数", UIPathMeasure1Fragment()),
                EntryF("3. getPosTan()函数", UIPathMeasure2Fragment()),
        ))
    }
}