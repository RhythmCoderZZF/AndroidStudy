package com.example.android_study.Java.io

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.Java.io.file.JavaIOFileActivity
import kotlinx.android.synthetic.main.activity_rv.*

class JavaIOActivity : BaseActivity() {
    private val list = listOf(
            Entry("1. separator和pathSeparator", JavaIOFileActivity::class.java),
    )

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
    }
}