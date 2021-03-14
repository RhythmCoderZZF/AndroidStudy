package com.example.android_study.ui_custom.study._Canvas

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._Canvas.Layer.UICanvasFragment

class _UICusStudyCanvasActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        setViewPagerFragment(window, listOf(
                EntryF("1.saveLayer", UICanvasFragment()),
        ))
    }
}