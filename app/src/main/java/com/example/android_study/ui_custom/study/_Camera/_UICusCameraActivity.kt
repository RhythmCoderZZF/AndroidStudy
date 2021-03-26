package com.example.android_study.ui_custom.study._Camera

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui_custom.study._Bitmap.UIBitmapFragment
import com.example.android_study.ui_custom.study._Bitmap._compress.UIBitmapFragment2
import com.example.android_study.ui_custom.study._Bitmap._pixel.UIBitmapFragment1
import com.example.android_study.ui_custom.study._Camera.flipImage.UICameraFlipViewFragment
import com.example.android_study.ui_custom.study._Matrix.UIMatrixFragment
import com.example.android_study.ui_custom.study._Packge_View.UIPackageFragment
import com.example.android_study.ui_custom.study._SurfaceView.UISurfaceViewFragment

class _UICusCameraActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        CmdUtil.showWindow()
        window.decorView.post{
            setViewPagerFragment(window, listOf(
                    EntryF("1.Camera", UICameraFragment()),
                    EntryF("2.Camera实现翻页效果", UICameraFlipViewFragment()),
            ))
        }
    }
}