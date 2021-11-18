package com.example.android_study.kotlin.flow.completion

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

class KotlinFlowCompletionActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_flow_completion

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /*————————————————《成功完成》————————————————————*/
        /*————————————————《1.try/finally》————————————————————*/
        btn.setOnClickListener {
            launch {
                val f = flow {
                    emit(1)
                }.transform {
                    emit("<$it>")
                }
                try {
                    f.collect {
                        CmdUtil.v("collect value:$it")
                    }
                } finally {
                    CmdUtil.i("Completion!")
                }
            }
        }
        /*————————————————《成功完成2》————————————————————*/
        btn1.setOnClickListener {
            launch {
                flow {
                    emit(1)
                }.onCompletion {
                    CmdUtil.i("Completion:$it")
                }.transform {
                    emit("<$it>")
                }.collect {
                    CmdUtil.v("collect value:$it")
                }
            }
        }

        /*————————————————《异常完成》————————————————————*/
        btn2.setOnClickListener {
            launch {
                flow {
                    emit(1)
                }.onCompletion {
                    CmdUtil.i("Completion:$it")
                }.transform {
                    emit("<$it>")
                    throw RuntimeException("error")
                }.catch {
                    CmdUtil.e("catch exception:${it.message}")
                }.collect {
                    CmdUtil.v("collect value:$it")
                }
            }
        }
    }
}