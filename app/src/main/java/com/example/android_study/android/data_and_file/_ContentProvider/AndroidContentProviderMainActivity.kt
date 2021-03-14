package com.example.android_study.android.data_and_file._ContentProvider

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.android.data_and_file.internal_and_outspace.InternalFragment
import com.example.android_study.android.data_and_file.internal_and_outspace.out.OutFragment

class AndroidContentProviderMainActivity  : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
            setViewPagerFragment(window, listOf(
            ))
    }
}