package com.example.android_study.jetpack.navigation.base

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity

/**
 * NavHost:显示导航图中目标的空白容器。导航组件包含一个默认 NavHost 实现 (NavHostFragment)，可显示 Fragment 目标。
 * NavGraph:图形化展示fragment之间的逻辑关系
 * NavController:根据NavGraph驱动fragment的切换
 */

class NavigationAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_navigation_ay
    override fun initViewAndData(savedInstanceState: Bundle?) {}
}