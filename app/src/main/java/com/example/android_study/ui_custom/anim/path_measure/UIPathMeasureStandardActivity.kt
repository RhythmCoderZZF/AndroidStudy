package com.example.android_study.ui_custom.anim.path_measure

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.anim.path_measure.one.UIPathMeasure1Fragment
import com.example.android_study.ui_custom.anim.path_measure.three.UIPathMeasureFragment
import com.example.android_study.ui_custom.anim.path_measure.two.UIPathMeasure2Fragment

class UIPathMeasureStandardActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1. 基础", UIPathMeasureFragment()),
                EntryF("2. getSegment()函数", UIPathMeasure1Fragment()),
                EntryF("3. getPosTan()函数", UIPathMeasure2Fragment()),
        ))
    }
}