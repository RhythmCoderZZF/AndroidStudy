package com.example.android_study.ui_custom.study._Paint

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._Paint.Xfermode.UIPaintXFermodeFragment

class _UICusStudyPaintActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        setViewPagerFragment(window, listOf(
                EntryF("StrokeCap|StrokeJoin", UIPaintStrokeCapStrokeJoinFragment()),
                EntryF("ShadowLayer|BlurMaskFilter", UIPaintShadowBlurFragment()),
                EntryF("Shader", UIPaintShaderFragment()),
                EntryF("XFermode", UIPaintXFermodeFragment()),
        ))
    }
}