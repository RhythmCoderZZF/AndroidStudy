package com.example.android_study.kotlin.coroutine.cancel

import android.os.Bundle
import com.bennyhuo.kotlin.coroutines.Job
import com.bennyhuo.kotlin.coroutines.dispatcher.Dispatchers
import com.bennyhuo.kotlin.coroutines.launch
import com.bennyhuo.kotlin.coroutines.scope.GlobalScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_coroutine_cancel_ay.*

/**
 * sample of cancel coroutine
 */
class CoroutineCancelAy : BaseActivity() {
    private var job: Job? = null
    override fun getLayoutId() = R.layout.activity_coroutine_cancel_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        button.setOnClickListener {
            if (job?.isActive == true) {
                job?.cancel()
                return@setOnClickListener
            }
            progressBar.progress = 0
            job = GlobalScope.launch {
                repeat(21) {
                    if (!scopeContext[Job]!!.isActive) return@repeat
                    CmdUtil.v("${fib(37)}")
                    launch(Dispatchers.Android) {
                        progressBar.progress = it * 5
                    }
                }
            }
        }
    }


    //斐波那契  密集计算
    fun fib(i: Int): Int {
        if (i == 0) return 0
        if (i == 1) return 1
        return fib(i - 1) + fib(i - 2)
    }
}