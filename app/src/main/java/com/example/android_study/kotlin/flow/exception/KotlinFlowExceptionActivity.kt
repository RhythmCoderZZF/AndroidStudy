package com.example.android_study.kotlin.flow.exception

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_flow_exception.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class KotlinFlowExceptionActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_flow_exception

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /*————————————————————————《违反异常透明原则的处理方式》——————————————————————*/
        val f = flow {
            try {
                emit(1)
            } catch (e: Exception) {
                CmdUtil.e(e.message)
            }
        }.map {
            throw RuntimeException("map error")
            "$it"
        }

        btn_start.setOnClickListener {
            launch {
                f.collect {
                    CmdUtil.v("collect value:$it")
                    throw RuntimeException("collect error")
                }
            }
        }

        /*————————————————————————《违反异常透明原则的处理方式》——————————————————————*/
        val f1 = flow {
            emit(1)
            throw RuntimeException("error")
        }

        btn_start1.setOnClickListener {
            launch {
                try {
                    f1.collect {
                        CmdUtil.v("collect value:$it")
                    }
                } catch (e: Exception) {
                    CmdUtil.e("${e.message}")
                }
            }
        }
        /*————————————————————————《onEach+collect操作符》——————————————————————*/
        val f2 = flow {
            emit(1)
            throw RuntimeException("error")
        }

        btn_start2.setOnClickListener {
            launch {
                f2.onEach {
                    CmdUtil.v("collect value:$it")
                    throw RuntimeException("collect error")
                }.catch {
                    CmdUtil.e("${it.message}")
                }.collect()
            }
        }
    }
}