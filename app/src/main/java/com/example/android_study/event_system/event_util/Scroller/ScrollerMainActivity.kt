package com.example.android_study.event_system.event_util.Scroller

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_util.Scroller.fling.ScrollerFlingActivity1
import com.example.android_study.event_system.event_util.Scroller.fling_view.ScrollerFlingActivity
import com.example.android_study.event_system.event_util.Scroller.scroll.ScrollerActivity
import com.example.android_study.event_system.event_util.Scroller.scroll_view.ScrollerActivity1
import kotlinx.android.synthetic.main.activity_rv.*

class ScrollerMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.scroll内容", ScrollerActivity::class.java),
                Entry("2.scroll控件", ScrollerActivity1::class.java),
                Entry("3.fling内容", ScrollerFlingActivity1::class.java),
                Entry("4.fling控件", ScrollerFlingActivity::class.java),

                )
        )
    }
}