package com.example.android_study.ui_custom.study._4_anim.anim

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF

class UICusAnimMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1.视图动画(xml)", UIABaseXmlFragment()),
                EntryF("2.视图动画(代码)", UIABaseCodeFragment()),
                EntryF("3.帧动画(xml)", UIABaseAnimListXmlFragment()),
                EntryF("4.帧动画(代码)", UIABaseAnimListCodeFragment())
        ))
    }
}