package com.example.android_study.kotlin._2_coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity__3__async_and_await_ay.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

/**
 * async{}.await()
 */
class _4_AsyncAndAwaitAy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__3__async_and_await_ay)
        CmdUtil.showWindow()

        button.setOnClickListener {
            CoroutineScope(IO).launch {
                val start = System.currentTimeMillis()
                val result1 = async {
                    getApi1()
                }.await()
                val result2 = async {
                    getApi2()
                }.await()

                withContext(Main) {
                    textView.text = "$result1 - $result2"
                    textView.append("\n${System.currentTimeMillis() - start}ms")//1500ms
                }
            }
        }
    }

    suspend fun getApi1(): String {
        delay(500)
        return "result 1"
    }

    suspend fun getApi2(): String {
        delay(1000)
        return "result 2"
    }
}