package com.example.android_study.ui_custom.study._Drawable

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.study._Drawable._drawable.UIDrawableFragment
import com.example.android_study.ui_custom.study._Drawable._shape_drawable.UIShapeDrawableFragment

class _UICusDrawableActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        setViewPagerFragment(window, listOf(
                EntryF("1.ShapeDrawable", UIShapeDrawableFragment()),
                EntryF("2.自定义Drawable", UIDrawableFragment()),
        ))
    }
}