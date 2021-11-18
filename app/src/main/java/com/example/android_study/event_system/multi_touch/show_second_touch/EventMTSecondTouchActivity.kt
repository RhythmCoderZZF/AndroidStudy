package com.example.android_study.event_system.multi_touch.show_second_touch

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.event_system.event_util.ViewDragHelper.demo_slide_menu.ViewDragHelperSlideMenuActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.drag.ViewDragHelperActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.drag_edge.ViewDragHelperEdgeActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.fling.ViewDragHelperFlingActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.scroll.ViewDragHelperScrollActivity
import kotlinx.android.synthetic.main.activity_rv.*

class EventMTSecondTouchActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_cus_event_multi_touch_show_second_touch

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }
}