package com.example.android_study.ui_custom.study._4_anim.svg

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._4_anim.animator.*
import com.example.android_study.ui_custom.study._4_anim.viewGroup_anim.UILayoutChangeAnimFragment
import com.example.android_study.ui_custom.study._4_anim.viewGroup_anim.UILayoutTransitionFragment

class _UISVGMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1. 初识SVG（Vector）", UISVGMainFragment()),
                EntryF("2. 动态Vector", UIAnimVectorFragment()),
        ))
    }
}