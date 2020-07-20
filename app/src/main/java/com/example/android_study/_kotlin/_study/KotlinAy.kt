package com.example.android_study._kotlin._study

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.adapter.Entry
import com.example.android_study.base.cmd.CmdUtil
import com.example.android_study.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity_kotlin_ay.*

class KotlinAy : BaseActivity() {


    var list = mutableListOf(
            Entry("类与继承", KotlinAy::class.java)
    )


    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(mContext, "Study")
        CmdUtil.showWindow()
        init()
    }

    private fun init() {
        addMainPageAdapter(rv,list)
    }


}