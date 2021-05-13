package com.example.android_study.ui.recyclerView.itemDecoration

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui.recyclerView.itemDecoration.fragments.ItemDecorationFragment
import com.example.android_study.ui.recyclerView.layoutManager.fragments.CustomLayoutManagerFragment

class RVItemDecorationActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(window, listOf(
              EntryF("1.自定义ItemDecoration", ItemDecorationFragment())
        ))
    }
}