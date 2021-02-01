package com.example.android_study.jetpack.navigation

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.jetpack.navigation.base.NavigationAy
import com.example.android_study.jetpack.navigation.bottom_navigation.BottomNavAy
import kotlinx.android.synthetic.main.activity_navigation_test.*

class NavigationTestActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_navigation_test
    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. 基础使用", NavigationAy::class.java),
                Entry("2. 底部导航栏", BottomNavAy::class.java)
        ))
    }
}