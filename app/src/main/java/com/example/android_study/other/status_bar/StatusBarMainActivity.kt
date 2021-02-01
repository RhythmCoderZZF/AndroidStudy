package com.example.android_study.other.status_bar

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.other.status_bar.immerse.StatusBarImmerseActivity
import kotlinx.android.synthetic.main.activity_status_bar_main.*

class StatusBarMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_status_bar_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. 沉浸式", StatusBarImmerseActivity::class.java)
        ))
    }
}