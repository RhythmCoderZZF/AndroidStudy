package com.example.android_study.jetpack.paging3

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.jetpack.paging3._main.JetPackPagingMainActivity
import kotlinx.android.synthetic.main.activity_paging_ay.*


class PagingAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_paging_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 基本使用", JetPackPagingMainActivity::class.java)
        ))
    }
}