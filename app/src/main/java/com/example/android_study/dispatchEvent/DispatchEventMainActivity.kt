package com.example.android_study.dispatchEvent

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.dispatchEvent.interceptEvent.DispatchEventInterceptEventActivity
import kotlinx.android.synthetic.main.activity_dispatch_event_main.*

class DispatchEventMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_dispatch_event_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. 事件拦截",DispatchEventInterceptEventActivity::class.java)
        ))
    }
}