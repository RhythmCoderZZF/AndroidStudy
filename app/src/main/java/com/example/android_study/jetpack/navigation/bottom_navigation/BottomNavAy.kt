package com.example.android_study.jetpack.navigation.bottom_navigation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_bottom_nav_ay.*

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 * 要点：
 * 1. bottomNavigationView.setupWithNavController(fragment.findNavController()) 绑定Controller
 * 2. BottomNavigationView绑定menu，menu中的id必须和NavGraph中定义fragment id相同，否则无法点击切换页面
 */
class BottomNavAy : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_bottom_nav_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        bottomNavigationView.setupWithNavController(fragment.findNavController())

    }
}