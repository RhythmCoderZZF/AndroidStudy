package com.example.android_study.kotlin.flow.buffer

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_buffer_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class KotlinFlowBufferMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_flow_buffer_main
    private suspend fun simple() = flow {
        for (i in 1..3) {
            delay(400)
            emit(i)
        }
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        //**1. 没有缓冲的流**
        btnNormal.setOnClickListener {
            launch {
                val time = measureTimeMillis {
                    simple().onEach {
                        CmdUtil.v("P:$it")
                    }.collect {
                        delay(1000)
                        CmdUtil.v(it.toString())
                    }
                }
                CmdUtil.v("cost:$time ms")
                CmdUtil.i("3*400+3*1000:同步生产消费")
            }
        }
        //**2. buffer创建缓冲区**
        btnBuffer.setOnClickListener {
            launch {
                val time = measureTimeMillis {
                    simple().onEach {
                        CmdUtil.v("P:$it")
                    }.buffer().collect {
                        delay(1000)
                        CmdUtil.v(it.toString())
                    }
                }
                CmdUtil.v("cost:$time ms")
                CmdUtil.i("1*400+3*1000:并行生产消费,来不及消费丢的到buffer中")
            }
        }
        //**3. 丢弃中间值**
        btnConflate.setOnClickListener {
            launch {
                val time = measureTimeMillis {
                    simple().onEach {
                        CmdUtil.v("P:$it")
                    }.conflate()
                            .collect {
                                delay(1000)
                                CmdUtil.v(it.toString())
                            }
                }
                CmdUtil.v("cost:$time ms")
                CmdUtil.i("1*400+2*1000:并行生产消费,来不及消费的新元素就丢掉")
            }
        }
        //**4. 只收集最新值**
        btnCollectLatest.setOnClickListener {
            launch {
                val time = measureTimeMillis {
                    simple().onEach {
                        CmdUtil.v("P:$it")
                    }.collectLatest {
                        delay(1000)
                        CmdUtil.v(it.toString())
                    }
                }
                CmdUtil.v("cost:$time ms")
                CmdUtil.i("3*400+1*1000:并行生产消费,来不及消费的协程会被取消,只消费最新的元素")
            }
        }
    }
}