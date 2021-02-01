package com.example.android_study.kotlin.coroutine

import android.os.Bundle
import android.widget.TextView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity__4__deal_with_exception.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

/**
 * children jobs throw Exception ，how parent deal with
 */
class _5_DealWithException : BaseActivity() {

    val parentExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val res = "^^^ERROR >>> ${throwable.message}"
        p(res, textView)
    }

    override fun getLayoutId() = R.layout.activity__4__deal_with_exception

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this, "处理异常")
        button.setOnClickListener {
            case1()
        }
        button1.setOnClickListener {
            case2()
        }
    }

    /*
         DefaultDispatcher-worker-1:LogUtil []: job1.getResult = 1
         DefaultDispatcher-worker-2:LogUtil []: 333ERROR >>> Parent job is Cancelling
         DefaultDispatcher-worker-1:LogUtil []: 222ERROR >>> job2: oops... something bad!
         DefaultDispatcher-worker-1:LogUtil []: ^^^ERROR >>> job2: oops... something bad!
         DefaultDispatcher-worker-1:LogUtil []: 444ERROR >>> job2: oops... something bad!
    */
    private fun case1() {
        val jobParent = CoroutineScope(IO).launch(parentExceptionHandler) {
            val job1 = launch {
                val res = "job1.getResult = ${getApi(1)}"
                p(res, textView)
            }
            val job2 = launch {
                val res = "job2.getResult = ${getApi(2).apply {
                    throw Exception("job2: oops... something bad!")
                }}"
                p(res, textView)
            }
            val job3 = launch {
                val res = "job3.getResult = ${getApi(3)}"
                p(res, textView)
            }
            job1.invokeOnCompletion {
                if (it != null) {
                    val res = "111ERROR >>> ${it.message}"
                    p(res, textView)
                }
            }
            job2.invokeOnCompletion {
                if (it != null) {
                    val res = "222ERROR >>> ${it.message}"
                    p(res, textView)
                }
            }
            job3.invokeOnCompletion {
                if (it != null) {
                    val res = "333ERROR >>> ${it.message}"
                    p(res, textView)
                }
            }

        }
        jobParent.invokeOnCompletion {
            if (it != null) {
                val res = "444ERROR >>> ${it.message}"
                p(res, textView)
            }
        }
    }


    /*
         DefaultDispatcher-worker-8:LogUtil []: job1.getResult = 1
         DefaultDispatcher-worker-8:LogUtil []: 222ERROR >>> job2: oops... something bad!
         DefaultDispatcher-worker-8:LogUtil []: job3.getResult = 3
    */
    private fun case2() {
        val jobParent = CoroutineScope(IO).launch(parentExceptionHandler) {
            val job1 = launch {
                val res = "job1.getResult = ${getApi(1)}"
                p(res, textView1)
            }
            val job2 = launch {
                val res = "job2.getResult = ${getApi(2).apply {
                    //注意：抛出CancellationException只会是job抛出异常并处理
                    throw CancellationException("job2: oops... something bad!")
                }}"
                p(res, textView1)
            }
            val job3 = launch {
                val res = "job3.getResult = ${getApi(3)}"
                p(res, textView1)
            }
            job1.invokeOnCompletion {
                if (it != null) {
                    val res = "111ERROR >>> ${it.message}"
                    p(res, textView1)
                }
            }
            job2.invokeOnCompletion {
                if (it != null) {
                    val res = "222ERROR >>> ${it.message}"
                    p(res, textView1)
                }
            }
            job3.invokeOnCompletion {
                if (it != null) {
                    val res = "333ERROR >>> ${it.message}"
                    p(res, textView1)
                }
            }

        }
        jobParent.invokeOnCompletion {
            if (it != null) {
                val res = "444ERROR >>> ${it.message}"
                p(res, textView1)
            }
        }
    }

    private fun p(msg: String, tex: TextView) {
        CmdUtil.v(msg)
        tex.post { tex.append(msg + "\n") }
    }

    suspend fun getApi(i: Int): Int {
        delay(500L * i)
        return i
    }
}