package com.example.android_study.kotlin.coroutine.coroutineLite

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_context.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

@ExperimentalStdlibApi
class CoroutineLiteActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_stdlib
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        part1()
    }

    private fun part1() {
        btn.setOnClickListener {
//            GlobalScope.launch {
//                throw IllegalArgumentException()
//            }

            launch {

                this.isActive
                throw IllegalArgumentException()
            }
        }
    }

}