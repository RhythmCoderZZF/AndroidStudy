package com.example.android_study.event_system.event_util

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_util.Scroller.ScrollerMainActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.ViewDragHelperMainActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UICusEventUtilActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.Scroller", ScrollerMainActivity::class.java),
                Entry("2.ViewDragHelper", ViewDragHelperMainActivity::class.java),
            )
        )
    }
}