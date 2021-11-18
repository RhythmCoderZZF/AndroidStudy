package com.example.android_study.kotlin.flow.base

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_base.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException

/**
 * Flow类似于Sequence，只不过依赖于协程
 *
 *
 */
class KotlinFlowBaseActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_flow_base

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        /* 1.启动一个flow*/
        flow.setOnClickListener {
            lifecycleScope.launch {
                //创建一个flow
                flow {
                    CmdUtil.v("flow构建器")
                    delay(500)
                    emit(0)
                    delay(500)
                    emit(1)
                    delay(500)
                    throw RuntimeException("崩溃><")
                    emit(2)
                }.catch {
                    CmdUtil.e("捕获异常：${it.message}")
                }.onCompletion {
                    CmdUtil.i("完成：${it?.message ?: "完成！"}")
                }.collect {                                                 //collect必须在协程上下文中
                    CmdUtil.v("接收端：$it")
                }
            }
        }

        /* 2.———————————————————————————————切换线程———————————————————————————————*/
        flow_emit_in_io.setOnClickListener {
            lifecycleScope.launch {
                flow {
                    CmdUtil.v("发射端:${Thread.currentThread().name}")
                    delay(500)
                    emit("a")
                    delay(500)
                    emitAll(listOf("b", "c").asFlow())
                }.flowOn(IO)
                    .collect {
                        CmdUtil.v("接收端：$it -> ${Thread.currentThread().name}")
                    }
            }
        }

        flow_emit_in_io2.setOnClickListener {
            lifecycleScope.launch {
                flow {
                    delay(500)
                    emit(withContext(IO) {
                        CmdUtil.v("发射端:${Thread.currentThread().name}")
                        "a"
                    })
                }.collect {
                    CmdUtil.v("接收端：$it -> ${Thread.currentThread().name}")
                }
            }
        }

        flow_collect_in_io.setOnClickListener {
            val flow = flow {
                CmdUtil.v("发射端:${Thread.currentThread().name}")
                delay(500)
                emit("a")
                delay(500)
                emitAll(listOf("b", "c").asFlow())
            }.flowOn(Main)
            lifecycleScope.launch(Default) {
                flow.collect {
                    CmdUtil.v("接收端：$it -> ${Thread.currentThread().name}")
                }
            }
        }

        btnFlow.setOnClickListener {
            launch {
                flow {
                    while (true) {
                        delay(500)
                        emit(1)
                    }
                }.catch {
                    CmdUtil.e(it.message)
                }.collect {
                    CmdUtil.v(it.toString())
                }
            }
        }
        /*3. ————————————————————————流的取消—————————————————————————————————*/
        flow_cancel.setOnClickListener {
            val flow = flow {
                var i = 0
                while (true) {
                    emit(i++)
                }
            }
            launch {
                flow.collect {
                    if (it == 3) {
                        cancel()
                    }
                    CmdUtil.v(it.toString())
                }
            }
        }

        flow_cancel_not_affect.setOnClickListener {
            val flow = (0..5).asFlow().cancellable()
            launch {
                flow.collect {
                    if (it == 3) {
                        cancel()
                    }
                    CmdUtil.v(it.toString())
                }
            }
        }

        /*____________________________________背压__________________________________________________
            背压：SubscribeOn指定的线程生产太快,消费慢
            几种处理方式：
            1. buffer：指定缓存大小buffer(int capacity)，默认capacity=0，则生产者需要等到消费者消费一个才能生产一个；若capacity>0，则生产者尽快生产到buffer中，不必等待消费者消费
            2. conflate：消费者尽力消费最新生产的元素，来不及消费的丢弃
            3. collectLatest：消费者来不及消费，丢弃该元素，且取消正在执行的步骤
        */
        flow_3_1.setOnClickListener {
            val flow = flow {
                repeat(5) {
                    delay(100)
                    CmdUtil.v("生产：$it")
                    emit(it)
                }
            }
            launch {
                flow.collect {
                    CmdUtil.i("消费ing:$it")
                    delay(500)
                    CmdUtil.e("消费：$it")
                }
            }
        }
        /* buffer模式 */
        flow_3.setOnClickListener {
            val flow = flow {
                repeat(10) {
                    delay(100)
                    CmdUtil.v("生产：$it")
                    emit(it)
                }
            }.buffer()
            launch {
                flow.collect {
                    CmdUtil.i("消费ing:$it")
                    delay(500)
                    CmdUtil.e("消费：$it")
                }
            }
        }
        /*
        conflate模式
        */
        flow_4.setOnClickListener {
            val flow = flow {
                repeat(10) {
                    delay(100)
                    CmdUtil.v("生产:$it")
                    emit(it)
                }
            }.conflate()
            launch {
                flow.collect {
                    CmdUtil.i("消费ing:$it")
                    delay(500)
                    CmdUtil.e("消费:$it")
                }
            }
        }
        /*
        collectLatest模式
        */
        flow_5.setOnClickListener {
            val flow = flow {
                repeat(10) {
                    delay(100)
                    CmdUtil.v("生产:$it")
                    emit(it)
                }
            }
            launch {
                flow.collectLatest {
                    CmdUtil.i("消费ing:$it")
                    delay(500)
                    CmdUtil.e("消费:$it")
                }
            }
        }
        /*———————————————————————————————————Competition—————————————————————————————————————————*/
        flow_6.setOnClickListener {
            launch {
                flow {
                    repeat(10) {
                        delay(100)
                        if (it == 3) {
                            throw IllegalArgumentException()
                        }
                        emit(it)
                    }
                }.onCompletion {
                    if (it == null) {
                        CmdUtil.i("完成")
                    } else {
                        CmdUtil.e("错误：${it.cause}")
                    }
                }.collect {
                    CmdUtil.v("$it")
                }
            }
        }
    }
}
