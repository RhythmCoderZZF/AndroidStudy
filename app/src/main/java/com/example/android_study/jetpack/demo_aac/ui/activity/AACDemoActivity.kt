package com.example.android_study.jetpack.demo_aac.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.jetpack.demo_aac.ui.viewmodel.MainViewModel

class AACDemoActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun getLayoutId(): Int {
        return R.layout.activity_a_a_c_demo
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {}


}