package com.example.android_study._kotlin._coroutines

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._kotlin._coroutines._1_base._1_Ay
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.adapter.Entry
import kotlinx.android.synthetic.main.activity_coroutines_ay.*

class CoroutinesAy : BaseActivity() {
    val list = listOf(Entry("1. 基础", _1_Ay::class.java))
    override fun getLayoutId() = R.layout.activity_coroutines_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        addMainPageAdapter(rv,list)
    }


}

