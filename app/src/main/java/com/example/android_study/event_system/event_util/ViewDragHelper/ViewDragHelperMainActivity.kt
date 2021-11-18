package com.example.android_study.event_system.event_util.ViewDragHelper

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_util.ViewDragHelper.demo_slide_menu.ViewDragHelperSlideMenuActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.drag.ViewDragHelperActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.drag_edge.ViewDragHelperEdgeActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.fling.ViewDragHelperFlingActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.scroll.ViewDragHelperScrollActivity
import kotlinx.android.synthetic.main.activity_rv.*

class ViewDragHelperMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
            Entry("1.拖拽", ViewDragHelperActivity::class.java),
            Entry("2.边缘拖拽", ViewDragHelperEdgeActivity::class.java),
            Entry("3.平滑滚动", ViewDragHelperScrollActivity::class.java),
            Entry("4.投掷", ViewDragHelperFlingActivity::class.java),
            Entry("5.demo仿qq侧边栏", ViewDragHelperSlideMenuActivity::class.java),
        ))
    }
}