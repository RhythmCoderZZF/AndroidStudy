package com.example.android_study.ui_custom.study._Bitmap

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui_custom.study._Bitmap._compress.UIBitmapFragment2
import com.example.android_study.ui_custom.study._Bitmap._pixel.UIBitmapFragment1

class _UICusBitmapActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        CmdUtil.showWindow()
        setViewPagerFragment(window, listOf(
                EntryF("1.Bitmap", UIBitmapFragment()),
                EntryF("1.像素改色", UIBitmapFragment1()),
                EntryF("2.压缩", UIBitmapFragment2()),
        ))
    }
}