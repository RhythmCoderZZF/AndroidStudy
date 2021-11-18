package com.example.android_study.ui.recyclerView.itemDecoration

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_meet.ItemDecorationFragment
import com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_simple.ItemDecorationSimpleFragment

class RVItemDecorationActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(window, listOf(
              EntryF("1.初识ItemDecoration", ItemDecorationFragment()),
              EntryF("2.ItemDecoration小案例", ItemDecorationSimpleFragment()),
        ))
    }
}