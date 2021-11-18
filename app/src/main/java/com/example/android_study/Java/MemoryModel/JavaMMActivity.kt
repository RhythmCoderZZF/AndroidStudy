package com.example.android_study.Java.MemoryModel

import android.os.Bundle
import com.example.android_study.Java.MemoryModel.String.JavaStringMMActivity
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import kotlinx.android.synthetic.main.activity_rv.*

class JavaMMActivity : BaseActivity() {

    private val list = listOf(
            Entry("1. 从String看Java内存模型", JavaStringMMActivity::class.java),
    )

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
    }
}