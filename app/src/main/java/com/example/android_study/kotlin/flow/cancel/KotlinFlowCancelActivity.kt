package com.example.android_study.kotlin.flow.cancel

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_flow_completion.*
import kotlinx.android.synthetic.main.activity_flow_exception.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class KotlinFlowCancelActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_flow_cancel

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /*————————————————《取消流》————————————————————*/
        btn.setOnClickListener {
            launch {
                flow {
                    repeat(5) {
                        emit(it)
                    }
                }.collect {
                    CmdUtil.v("collect value:$it")
                    if (it == 3) {
                        cancel()
                    }
                }
            }
        }
    }
}