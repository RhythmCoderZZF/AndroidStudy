package com.example.android_study.android.location

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.handler.threadLocal.HandlerThreadLocalActivity
import com.example.android_study.android.handler.useInThread.HandlerThreadActivity
import kotlinx.android.synthetic.main.activity_handler_main.*

class LocationMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 LocationManager", AndroidLocationManagerMainActivity::class.java),
        ))
    }
}