package com.example.android_study.kotlin.flow.stateFlow_sharedFlow

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_flow_state_flow_shared_flow.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class KotlinStateFlowSharedFlowActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_flow_state_flow_shared_flow

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /* ————————————————————————————1.StateFlow ——————————————————————————*/
        val stateFlow = MutableStateFlow(0)
        btn_minus.setOnClickListener {
            stateFlow.value--
        }
        btn_add.setOnClickListener {
            stateFlow.value++
        }
        launch {
            stateFlow.collectLatest {
                tv.text = it.toString()
            }
        }
        /* ————————————————————————————2.SharedFlow ——————————————————————————*/
        val sharedFlow = MutableSharedFlow<Long>()
        btn_start.setOnClickListener {
            launch(IO) {
                while (true) {
                    sharedFlow.emit(System.currentTimeMillis())
                    CmdUtil.v("${System.currentTimeMillis()}")
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            sharedFlow.collectLatest {
                tv1.text = it.toString()
            }
        }
    }
}