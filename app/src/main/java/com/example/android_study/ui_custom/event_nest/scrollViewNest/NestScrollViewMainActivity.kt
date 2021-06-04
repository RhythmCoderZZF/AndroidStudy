package com.example.android_study.ui_custom.event_nest.scrollViewNest

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_u_i_cus_event_nest_scroll_view_nest.*

class NestScrollViewMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_cus_event_nest_scroll_view_nest

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btnNestScroll.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                scC.isNestedScrollingEnabled = true
                scP.isNestedScrollingEnabled = true
            } else {
                scC.isNestedScrollingEnabled = false
                scP.isNestedScrollingEnabled = false
            }
        }
    }
}