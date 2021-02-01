package com.example.android_study.kotlin.flow.networkSample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_network_sample.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class KotlinFlowNetworkSampleActivity : BaseActivity() {

    private val viewModel by viewModels<SampleViewModel>()

    override fun getLayoutId() = R.layout.activity_kotlin_flow_network_sample

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        api.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getWanHomePage(1).onStart {
                    CmdUtil.v("onStart")
                    Toast.makeText(this@KotlinFlowNetworkSampleActivity, "开始加载", Toast.LENGTH_SHORT).show()
                }.onCompletion {
                    CmdUtil.v("onCompletion")
                    Toast.makeText(this@KotlinFlowNetworkSampleActivity, "加载完成啦", Toast.LENGTH_SHORT).show()
                }.collectLatest {
                    CmdUtil.v("collectLatest")
                    Toast.makeText(this@KotlinFlowNetworkSampleActivity, "$it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}