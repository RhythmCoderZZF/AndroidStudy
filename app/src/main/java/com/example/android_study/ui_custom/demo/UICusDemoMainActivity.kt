package com.example.android_study.ui_custom.demo

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui_custom.demo.demo_map.UIPathMeasureDemoMapActivity
import com.example.android_study.ui_custom.demo.flipbord.UICusFlipViewActivity
import com.example.android_study.ui_custom.demo.foldingLayout.UICusFoldingLayoutActivity
import com.example.android_study.ui_custom.demo.standard.UICusStandardDemoActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UICusDemoMainActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("基础小案例合集", UICusStandardDemoActivity::class.java),
                Entry("路径小人", UIPathMeasureDemoMapActivity::class.java),
                Entry("翻页效果", UICusFlipViewActivity::class.java),
                Entry("折叠FoldLayout", UICusFoldingLayoutActivity::class.java),
            )
        )
    }
}