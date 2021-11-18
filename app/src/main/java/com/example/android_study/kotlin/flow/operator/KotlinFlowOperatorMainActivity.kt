package com.example.android_study.kotlin.flow.operator

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_flow_operator_main.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class KotlinFlowOperatorMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_kotlin_flow_operator_main


    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        //**1. 过渡操作符**
        btnMap.setOnClickListener {
            //map
            launch {
                (0..3).asFlow()
                    .map { element ->
                        performReq(element)
                    }
                    .collect {
                        CmdUtil.v(it)
                    }
            }
        }
        btnFilter.setOnClickListener {
            //filter
            launch {
                (0..3).asFlow()
                    .filter { element ->
                        element > 0
                    }.map { element ->
                        performReq(element)
                    }
                    .collect { element ->
                        CmdUtil.v(element)
                    }
            }
        }

        //**2. 转换操作符**
        btnTransform.setOnClickListener {
            //transform
            launch {
                (0..3).asFlow()
                    .transform {
                        emit(performReq(it))
                        emit("跟踪数据")
                    }
                    .collect {
                        CmdUtil.v(it)
                    }
            }
        }
        //**3. 限长操作符**
        btnTake.setOnClickListener {
            //take
            launch {
                (0..3).asFlow()
                    .transform {
                        try {
                            emit(performReq(it))
                        } catch (e: Exception) {
                            CmdUtil.e(e.toString())
                        }
                    }
                    .take(2)
                    .collect {
                        CmdUtil.v(it)
                    }
            }
        }

        btnDrop.setOnClickListener {
            //drop
            launch {
                (0..3).asFlow()
                    .transform {
                        try {
                            emit(performReq(it))
                        } catch (e: Exception) {
                            CmdUtil.e(e.toString())
                        }
                    }
                    .drop(2)
                    .collect {
                        CmdUtil.v(it)
                    }
            }
        }
        //**4. 末端操作符**
        btnReduce.setOnClickListener {
            //reduce
            launch {
                val result = (0..4).asFlow()
                    .reduce { accumulator, value ->
                        CmdUtil.v("accumulator:$accumulator | value:$value")
                        delay(1000)
                        accumulator + value
                    }
                CmdUtil.v("result:$result")
            }
        }
        btnFold.setOnClickListener {
            //fold
            launch {
                val result = (0..4).asFlow()
                    .fold(1) { accumulator, value ->
                        CmdUtil.v("accumulator:$accumulator | value:$value")
                        delay(1000)
                        accumulator + value
                    }
                CmdUtil.v("result:$result")
            }
        }
        btnToList.setOnClickListener {
            //toList
            launch {
                val result = (1..4).asFlow()
                    .transform {
                        delay(500)
                        CmdUtil.v("$it")
                        emit(it)
                    }.toList()
                CmdUtil.v("result:$result")
            }
        }
        //**5. FlowOn操作符**
        btnFlowOn.setOnClickListener {
            //toList
            launch {
                (1..4).asFlow()
                    .map {
                        CmdUtil.i("1@${Thread.currentThread().name}")
                        performReq(it)
                    }
                    .flowOn(IO)
                    .map {
                        CmdUtil.e("2@${Thread.currentThread().name}")
                        "$it!!"
                    }
                    .collect {
                        CmdUtil.v("result:$it")
                    }
            }
        }
        //**6. 组合多个流**
        btnZip.setOnClickListener {
            //zip
            launch {
                val flow1 = (1..4).asFlow().onEach { delay(300) }
                val flow2 = flowOf("one", "two", "three").onEach { delay(500) }
                val time = measureTimeMillis {
                    flow1.zip(flow2) { element1, element2 ->
                        "$element1 -> $element2"
                    }.collect {
                        CmdUtil.v(it)
                    }
                }
                CmdUtil.i("cost:$time ms")
                CmdUtil.i("合并两个流,元素一一对应")
            }
        }

        btnCombine.setOnClickListener {
            //combine
            launch {
                val flow1 = (1..4).asFlow().onEach { delay(300) }
                val flow2 = flowOf("one", "two", "three").onEach { delay(500) }
                val time = measureTimeMillis {
                    flow1.combine(flow2) { element1, element2 ->
                        "$element1 -> $element2"
                    }.collect {
                        CmdUtil.v(it)
                    }
                }
                CmdUtil.i("cost:$time ms")
                CmdUtil.i("合并两个流,元素交错对应")
            }
        }
        //**6. 展平流——————————**
        btnFlowNest.setOnClickListener {
            //嵌套Flow
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.map {
                    CmdUtil.v("map")
                    requestFlow(it)
                }.collect { flow ->
                    CmdUtil.v("<")
                    flow.collect { value ->
                        CmdUtil.v("<<")
                        requestFlowResult(value).collect { result ->
                            CmdUtil.v("<<<")
                            CmdUtil.i("$result cost ${System.currentTimeMillis() - startTime} ms")
                        }
                    }
                }
            }
        }
        btnFlattenConcat.setOnClickListener {
            //flattenConcat展平
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.map {
                    requestFlow(it)
                }.flattenConcat()
                    .map {
                        requestFlowResult(it)
                    }
                    .flattenConcat()
                    .collect { value ->
                        CmdUtil.i("$value cost ${System.currentTimeMillis() - startTime} ms")
                    }
            }
        }
        btnMyFlattenConcat.setOnClickListener {
            /*自实现flattenConcat*/
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.map {
                    CmdUtil.v("map")
                    requestFlow(it)
                }.let {
                    CmdUtil.v("let")
                    val f = flow {
                        CmdUtil.v("flow")
                        it.collect {
                            CmdUtil.v("collect1")
                            it.collect {
                                CmdUtil.v("collect2")
                                emit(it)
                            }
                        }
                    }
                    f
                }.collect { value ->
                    CmdUtil.i("$value cost ${System.currentTimeMillis() - startTime} ms")
                }
            }
        }
        btnFlatMapConcat.setOnClickListener {
            //flatMapConcat展平
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.flatMapConcat {
                    requestFlow(it)
                }.flatMapConcat {
                    requestFlowResult(it)
                }.collect { value ->
                    CmdUtil.i("$value cost ${System.currentTimeMillis() - startTime} ms")
                    CmdUtil.e("相当于collect机制")
                }
            }

        }
        btnFlatMapMerge.setOnClickListener {
            //flatMapMerge展平
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.flatMapMerge {
                    requestFlow(it)
                }.flatMapMerge {
                    requestFlowResult(it)
                }.collect { value ->
                    CmdUtil.i("$value cost ${System.currentTimeMillis() - startTime} ms")
                    CmdUtil.e("相当于buffer机制")
                }
            }

        }
        btnFlatMapLatest.setOnClickListener {
            //flatMapLatest展平
            launch {
                val startTime = System.currentTimeMillis() // 记录开始时间
                (1..3).asFlow().onEach {
                    CmdUtil.v("发送请求参数$it")
                    delay(100)
                }.flatMapLatest {
                    requestFlow(it)
                }.flatMapLatest {
                    requestFlowResult(it)
                }.collect { value ->
                    CmdUtil.i("$value cost ${System.currentTimeMillis() - startTime} ms")
                    CmdUtil.e("相当于collectLatest机制")
                }
            }

        }
    }

    suspend fun performReq(i: Int): String {
        delay(1000)
        return "result is $i"
    }

    fun requestFlow(i: Int): Flow<Int> = flow {
        delay(300)
        CmdUtil.v("修改Int")
        emit(i + 10)
    }

    fun requestFlowResult(i: Int): Flow<String> = flow {
        delay(500)
        CmdUtil.v("Int转换成String")
        emit("获得第二次请求参数:$i")
    }
}