package com.example.android_study.android.handler.useInThread

import android.os.*
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class HandlerThreadActivity : BaseActivity() {
    private lateinit var myThread: MyThread
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler

    override fun getLayoutId() = R.layout.activity_handler_thread

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    override fun run() {
        //1 自己写的handlerThread
        myThread = MyThread().apply {
            start()
        }

        //2.google的HandlerThread
        handlerThread = HandlerThread("thread-0")
        handlerThread.start()
        handler = Handler(handlerThread.looper) { msg ->
            CmdUtil.v(msg.obj.toString())
            handler.sendMessageDelayed(Message.obtain().apply { obj = "oops!" }, 550)
            true
        }
        handler.sendMessage(Message.obtain().apply { obj = "oops!" })
    }

    override fun onDestroy() {
        super.onDestroy()
        myThread.quit()
        handlerThread.quit()
    }

    private inner class MyThread : Thread() {
        lateinit var mHandler: Handler
        lateinit var mLooper: Looper
        override fun run() {
            Looper.prepare()
            mLooper = Looper.myLooper()!!
            mHandler = Handler { msg ->
                CmdUtil.i(msg.obj.toString())
                mHandler.sendMessageDelayed(Message.obtain().apply { obj = "hi~" }, 500)
                true
            }
            mHandler.sendMessage(Message.obtain().apply { obj = "hi~" })
            Looper.loop()
        }

        fun quit() {
            mLooper.quit()
        }
    }

}