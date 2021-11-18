package com.example.android_study.kotlin.channel

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.channel.base.KotlinChannelBaseActivity
import kotlinx.android.synthetic.main.activity_rv.*

class KotlinChannelMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 基础知识", KotlinChannelBaseActivity::class.java,"基础使用"),
        ))
    }
}