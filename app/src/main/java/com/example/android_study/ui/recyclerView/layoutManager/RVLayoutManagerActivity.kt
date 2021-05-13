package com.example.android_study.ui.recyclerView.layoutManager

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui.recyclerView.layoutManager.fragments.CustomLayoutManagerFragment

class RVLayoutManagerActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(window, listOf(
              EntryF("1.自定义LayoutManager", CustomLayoutManagerFragment())
        ))
    }
}