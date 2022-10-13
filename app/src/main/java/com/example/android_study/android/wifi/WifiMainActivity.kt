package com.example.android_study.android.wifi

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import kotlinx.android.synthetic.main.activity_rv.*

class WifiMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. ConnectionManager", ConnectionManagerActivity::class.java, "viewPager2+MediaPlayer"),
                Entry("2. WifiNetworkSuggestion", WifiNetworkSuggestionActivity::class.java),
        ))
    }
}