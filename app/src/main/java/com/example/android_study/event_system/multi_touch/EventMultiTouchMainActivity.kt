package com.example.android_study.event_system.multi_touch

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_util.Scroller.ScrollerMainActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.ViewDragHelperMainActivity
import com.example.android_study.event_system.multi_touch.base_print.EventMTBaseTouchActivity
import com.example.android_study.event_system.multi_touch.scale_image.EventMTScaleImageActivity
import com.example.android_study.event_system.multi_touch.show_second_touch.EventMTSecondTouchActivity
import kotlinx.android.synthetic.main.activity_rv.*

class EventMultiTouchMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.多点触摸打印", EventMTBaseTouchActivity::class.java),
                Entry("2.双指触摸", EventMTSecondTouchActivity::class.java),
                Entry("2.手势缩放图片", EventMTScaleImageActivity::class.java),
            )
        )
    }
}