package com.example.android_study.kotlin.flow.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_base.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

/**
 * Flow类似于Sequence，只不过依赖于协程
 *
 *
 */
class KotlinFlowBaseActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_flow_base

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        /*启动一个flow*/
        flow.setOnClickListener {
            flow_tex.text = ""

            lifecycleScope.launch {
                //创建一个flow
                val flow = flow {
                    emit(0)
                    delay(500)
                    emit(1)
                    throw RuntimeException("崩溃><")
                    emit(2)
                }


                flow.catch {
                    flow_tex.append("\n${it.message}")
                }.onCompletion {
                    flow_tex.append("\n${it?.message ?: "完成！"}")
                }.collect {                                                 //collect必须在协程上下文中
                    CmdUtil.v("$it -> ${Thread.currentThread().name}")
                    flow_tex.append("$it ")
                }
            }
        }


        /*
        在不同线程调度
        1. flowOn可以指定flow代码块执行的线程——RxJava的subscribeOn
        2. collect默认是在协程创建的调度器执行——RxJava的observeOn
        */
        flow_2.setOnClickListener {
            flow_tex_2.text = ""
            lifecycleScope.launch {
                flow {
                    emit("a")
                    CmdUtil.v("<-${Thread.currentThread().name}")
                    delay(500)
                    emitAll(listOf("b", "c.txt").asFlow())
                    CmdUtil.v("<-${Thread.currentThread().name}")
                }.flowOn(IO)
                        .collect {
                            CmdUtil.v("$it -> ${Thread.currentThread().name}")
                            flow_tex_2.append("$it ")
                        }
            }
        }
        var job: Job? = null
        btnFlow.setOnClickListener {
            job?.cancel(CancellationException("取消啦"))
            job = launch {
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
        /*____________________________________背压__________________________________________________

            背压：SubscribeOn指定的线程生产太快,消费慢
            几种处理方式：
            1. buffer：指定缓存大小buffer(int capacity)，默认capacity=0，则生产者需要等到消费者消费一个才能生产一个；若capacity>0，则生产者尽快生产到buffer中，不必等待消费者消费
            2. conflate：消费者尽力消费最新生产的元素，来不及消费的丢弃
            3. collectLatest：消费者来不及消费，丢弃该元素，且取消正在执行的步骤
        */

        flow_3.setOnClickListener {
            flow_tex_3.text = "无背压处理|"
            lifecycleScope.launch {
                flow {
                    repeat(10) {
                        emit(it)
                        delay(200)
                        CmdUtil.v("生产>$it")
                    }
                }.buffer(0).flowOn(IO).collect {
                    CmdUtil.i("消费<$it")
                    delay(500)
                    flow_tex_3.append("$it ")
                }

                /*buffer*/  flow_tex_3.append("\nbuffer|")
                flow {
                    repeat(10) {
                        emit(it)
                        delay(200)
                        CmdUtil.v("生产>$it")
                    }
                }.buffer(10).flowOn(IO).collect {
                    CmdUtil.i("消费<$it")
                    delay(500)
                    flow_tex_3.append("$it ")
                }
            }
        }
        /*
        conflate模式
        */
        flow_4.setOnClickListener {
            flow_tex_4.text = "conflate|"
            lifecycleScope.launch {
                flow {
                    repeat(10) {
                        delay(200)
                        emit(it)
                        CmdUtil.v("生产>$it")
                    }
                }.flowOn(IO).conflate().collect {
                    CmdUtil.i("消费<$it")
                    delay(500)
                    flow_tex_4.append("$it ")
                }
            }
        }
        /*
        collectLatest模式
        */
        flow_5.setOnClickListener {
            flow_tex_5.text = "collectLatest|"
            lifecycleScope.launch {
                flow {
                    repeat(10) {
                        delay(200)
                        emit(it)
                        CmdUtil.v("生产>$it")
                    }
                }.flowOn(IO).collectLatest {
                    CmdUtil.i("消费<$it")
                    delay(500)
                    flow_tex_5.append("$it ")
                }
            }
        }
    }
}
