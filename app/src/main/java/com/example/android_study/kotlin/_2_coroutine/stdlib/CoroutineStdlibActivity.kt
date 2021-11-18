package com.example.android_study.kotlin._2_coroutine.stdlib

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_coroutine_context.*
import kotlinx.coroutines.*
import kotlinx.coroutines.intrinsics.startCoroutineCancellable
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread
import kotlin.coroutines.*

@ExperimentalStdlibApi
class CoroutineStdlibActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_coroutine_stdlib
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val a = AtomicReference<State>()

        launch { }
    }

    sealed class State {
        class Fail : State()
        class Success : State()
    }
}