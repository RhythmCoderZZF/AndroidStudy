package com.example.android_study.network.okhttp.use

import android.graphics.RectF
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.network.okhttp.use.fragments.OkHttpUseFragment

class OkHttpUseActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
      setViewPagerFragment(window, listOf(
          EntryF("1.下载", OkHttpUseFragment())
      ))
    }
}