package com.example.android_study.ui_custom.event_util.Scroller.fling_view

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlin.random.Random


class ScrollerFlingActivity : BaseActivity() {
    private var mDataCount = 0

    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_scroller_fling

    override fun initViewAndData(savedInstanceState: Bundle?) {
    }


    private fun createBarInfo(): List<ChartView.BarInfo> {
        val data: MutableList<ChartView.BarInfo> = ArrayList()
        for (i in 1..mDataCount) {
            data.add(ChartView.BarInfo(i.toString() + "日", Random.nextDouble()))
        }
        return data
    }
}