package com.example.android_study.ui_custom.study._1_standard

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._1_standard.part1.UIStandardFragment
import com.example.android_study.ui_custom.study._1_standard.part2.UIStandardPathFragment
import com.example.android_study.ui_custom.study._Paint.UIPaintShadowBlurFragment

class UICusStandardActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(window, listOf(
                EntryF("1.基础", UIStandardFragment()),
                EntryF("2.Path", UIStandardPathFragment()),
                EntryF("3.Text", UIStandardTextFragment()),
                EntryF("4.Paint", UIPaintShadowBlurFragment()),
                EntryF("5.Canvas", UIStandardCanvasFragment()),

        ))
    }
}