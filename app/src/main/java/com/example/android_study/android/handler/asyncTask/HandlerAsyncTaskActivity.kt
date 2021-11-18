package com.example.android_study.android.handler.asyncTask

import android.os.*
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_handler_async_task.*
import java.util.concurrent.TimeUnit

class HandlerAsyncTaskActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_handler_async_task

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()


        btn_serial.setOnClickListener {
            repeat(5) {
                MyAsyncTask().execute(it.toString())
            }
        }
        btn_pool.setOnClickListener {
            repeat(5) {
                MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, it.toString())
            }
        }
    }

    private inner class MyAsyncTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String): String {
            val t = params[0]
            CmdUtil.v("$t:开始执行...")
            Thread.sleep(1000)
            CmdUtil.i("$t:执行完毕!")
            return params[0]
        }
    }
}