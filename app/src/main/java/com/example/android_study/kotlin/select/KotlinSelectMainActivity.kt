package com.example.android_study.kotlin.select

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_select.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class KotlinSelectMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_select

    @ExperimentalCoroutinesApi
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn.setOnClickListener {
            launch {
                CmdUtil.v(selectFizzOrBuzz(fizz(), buzz()))
            }
        }
    }

    fun fizz() = produce {
        delay(500)
        send("Fizz")
    }

    fun buzz() = produce {
        delay(1000)
        send("Buzz!")
    }


    private suspend fun selectFizzOrBuzz(
        fizz: ReceiveChannel<String>,
        buzz: ReceiveChannel<String>
    ) = select<String> {
        fizz.onReceive { value ->
            CmdUtil.v("1:$value")
            value
        }
        buzz.onReceive { value ->
            CmdUtil.v("2:$value")
            value
        }
    }

}