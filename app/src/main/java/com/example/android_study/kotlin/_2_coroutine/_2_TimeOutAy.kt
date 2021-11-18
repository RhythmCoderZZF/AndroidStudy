package com.example.android_study.kotlin._2_coroutine

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity__1__ay.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

/**
 * parent job timeOut , cancel children jobs
 */
class _2_TimeOutAy : BaseActivity(), Runnable {
    var job: Job? = null

    override fun getLayoutId() = R.layout.activity__1__ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.postDelayed(this, 200)
        btn0_1.setOnClickListener {
            job?.cancel()
        }
    }

    override fun run() {
        btn0.setOnClickListener {
            tex0.text = ""
            job = CoroutineScope(IO).launch {
                val apiFromNet = getApiFromNet()
                CmdUtil.v("获取结果：${apiFromNet}")
                val apiFromNet1 = getApiFromNet1()
                CmdUtil.v("获取结果：${apiFromNet1}")
                setTextOnMainThread("$apiFromNet - $apiFromNet1")
            }
            CmdUtil.v("非阻塞主线程")
        }

    }

    suspend fun getApiFromNet() = withContext(IO) {
        delay(1000)
        "result#1"
    }

    //return null if exceeded
    suspend fun getApiFromNet1() = withTimeoutOrNull(900) {
        delay(1000)
        "result#2"
    }

    suspend fun setTextOnMainThread(text: String) {
        withContext(Main) {
            tex0.text = text
        }
    }

}