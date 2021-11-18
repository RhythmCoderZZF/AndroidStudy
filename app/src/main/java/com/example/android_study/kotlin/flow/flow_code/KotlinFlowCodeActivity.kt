package com.example.android_study.kotlin.flow.flow_code

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_operator_main.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class KotlinFlowCodeActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_flow_code

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        launch {
            flow {
                emit(1)
                throw RuntimeException()
            }
        }
    }
}