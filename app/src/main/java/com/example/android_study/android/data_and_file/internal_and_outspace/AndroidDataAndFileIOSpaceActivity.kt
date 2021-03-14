package com.example.android_study.android.data_and_file.internal_and_outspace

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.android.data_and_file.internal_and_outspace.out.OutFragment

class AndroidDataAndFileIOSpaceActivity  : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
            setViewPagerFragment(window, listOf(
                    EntryF("1.内部存储",InternalFragment()),
                    EntryF("2.外部存储", OutFragment())
            ))
    }
}