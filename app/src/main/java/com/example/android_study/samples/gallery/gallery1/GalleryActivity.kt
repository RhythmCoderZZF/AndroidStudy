package com.example.android_study.samples.gallery.gallery1

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.ToolbarHelper

class GalleryActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_gallery
    init {
        showLifecycle=true
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this,"简易画廊")
    }
}