package com.example.android_study.android.handler

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.handler.threadLocal.HandlerThreadLocalActivity
import com.example.android_study.android.handler.useInThread.HandlerThreadActivity
import kotlinx.android.synthetic.main.activity_handler_main.*

class HandlerMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_handler_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 ThreadLocal", HandlerThreadLocalActivity::class.java,"ThreadLocal在Handler体系中的重要作用"),
                Entry("2 Handler Thread", HandlerThreadActivity::class.java,"子线程创建使用handler")
        ))
    }
}