package com.example.android_study.ui.viewpager2

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui.viewpager2.fragmentLifecycle.ViewPager2FragmentLifecycleActivity
import kotlinx.android.synthetic.main.activity_u_i_view_pager2_main.*

class UIViewPager2MainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_view_pager2_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. ViewPager2 Fragment懒加载", ViewPager2FragmentLifecycleActivity::class.java, "ViewPager2默认实现了Fragment懒加载技术,非可见Fragment不会调用onResume"),
        ))
    }
}