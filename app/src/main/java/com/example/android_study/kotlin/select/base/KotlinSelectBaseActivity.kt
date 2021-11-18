package com.example.android_study.kotlin.select.base

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

class KotlinSelectBaseActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_select_base

    @ExperimentalCoroutinesApi
    override fun initViewAndData(savedInstanceState: Bundle?) {

    }

    fun CoroutineScope.fizz() = produce<String> {
        while (true) { // 每 300 毫秒发送一个 "Fizz"
            delay(300)
            send("Fizz")
        }
    }
    fun CoroutineScope.buzz() = produce<String> {
        while (true) { // 每 500 毫秒发送一个"Buzz!"
            delay(500)
            send("Buzz!")
        }
    }

    suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
        select<Unit> { // <Unit> 意味着该 select 表达式不返回任何结果
            fizz.onReceive { value ->  // 这是第一个 select 子句
                println("fizz -> '$value'")
            }
            buzz.onReceive { value ->  // 这是第二个 select 子句
                println("buzz -> '$value'")
            }
        }
    }
}