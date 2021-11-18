package com.example.android_study.kotlin.select

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.select.base.KotlinSelectBaseActivity
import kotlinx.android.synthetic.main.activity_rv.*

class KotlinSelectMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 基础知识", KotlinSelectBaseActivity::class.java,"基础使用"),
        ))
    }
}