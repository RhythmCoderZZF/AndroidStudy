package com.example.android_study.kotlin.flow

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.flow.buffer.KotlinFlowBufferMainActivity
import com.example.android_study.kotlin.flow.flow_livedata.KotlinFlowAndLiveDataActivity
import com.example.android_study.kotlin.flow.main.KotlinFlowBaseActivity
import com.example.android_study.kotlin.flow.networkSample.KotlinFlowNetworkSampleActivity
import com.example.android_study.kotlin.flow.operator.KotlinFlowOperatorMainActivity
import com.example.android_study.kotlin.flow.switchThread.kotlinFlowSwitchThreadActivity
import kotlinx.android.synthetic.main.activity_kotlin_flow_main.*

class KotlinFlowMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_kotlin_flow_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 Base", KotlinFlowBaseActivity::class.java,"基础使用，背压"),
                Entry("2 项目中使用示例", KotlinFlowNetworkSampleActivity::class.java),
                Entry("3 操作符", KotlinFlowOperatorMainActivity::class.java),
                Entry("4 缓冲", KotlinFlowBufferMainActivity::class.java),
                Entry("5 线程切换的要点", kotlinFlowSwitchThreadActivity::class.java,"dispatcher是怎样修改上有流所在的线程"),
                Entry("6 Flow和LiveData的转换", KotlinFlowAndLiveDataActivity::class.java,"dispatcher是怎样修改上有流所在的线程"),
        ))
    }
}