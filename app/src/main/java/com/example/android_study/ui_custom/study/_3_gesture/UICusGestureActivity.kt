package com.example.android_study.ui_custom.study._3_gesture

import android.os.Bundle
import android.view.Choreographer
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui_custom.study._3_gesture._1_interface.UICusGestureInterfaceActivity
import com.example.android_study.ui_custom.study._3_gesture.demo.my_view_pager.UICusGestureMyViewPagerActivity
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.android.synthetic.main.activity_u_i_choreographer.*

class UICusGestureActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. GestureDetector",UICusGestureInterfaceActivity::class.java),
                Entry("2. D_MyViewPager", UICusGestureMyViewPagerActivity::class.java)
        ))
    }
}