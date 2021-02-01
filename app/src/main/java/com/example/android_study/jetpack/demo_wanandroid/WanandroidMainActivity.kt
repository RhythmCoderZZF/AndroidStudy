package com.example.android_study.jetpack.demo_wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android_study.R
import kotlinx.android.synthetic.main.activity_bottom_nav_ay.*

/**
 * 要点：
 * 1. plugin安装 json to kotlin插件生成kotlin data class
 */
class WanandroidMainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_wanandroid)
        bottomNavigationView.setupWithNavController(fragment.findNavController())
    }
}