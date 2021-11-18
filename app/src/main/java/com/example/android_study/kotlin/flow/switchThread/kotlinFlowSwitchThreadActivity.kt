package com.example.android_study.kotlin.flow.switchThread

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_switch_thread.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class kotlinFlowSwitchThreadActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_kotlin_flow_switch_thread

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        var f: Flow<Int>? = null
        launch(IO) {
            f = flow {
                CmdUtil.v("start emit...")
                delay(1000)
                emit(1)
            }
        }
        btn_start.setOnClickListener {
            launch {
                f?.collect {
                    CmdUtil.v("collect value:$it")
                }
            }
        }
    }
}