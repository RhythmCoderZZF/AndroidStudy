package com.example.android_study.performance.crash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_performance_crash.*
import kotlin.concurrent.thread

/**
 * Author:create by RhythmCoder
 * Date:2021/7/28
 * Description:
 */
class PerformanceCrashActivity : BaseActivity() {
    private var quit = false
    private var originDefaultHandler: Thread.UncaughtExceptionHandler? = null
    override fun getLayoutId() = R.layout.activity_performance_crash

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        initCrashHandler()

        btn.setOnClickListener {
            throw RuntimeException("Main Crash")
        }
        btn1.setOnClickListener {
            thread {
                throw RuntimeException("Thread Crash...")
            }
        }
    }

    private fun initCrashHandler() {
        Handler(Looper.getMainLooper()).postAtFrontOfQueue {
            while (!quit) {
                try {
                    Looper.loop()
                } catch (e: Exception) {
                    if (e.cause?.localizedMessage == "finish") {
                        CmdUtil.v("isfinish")
                        quit = true
                    }
                    Thread.getDefaultUncaughtExceptionHandler()
                        ?.uncaughtException(Thread.currentThread(), e)
                }
            }
        }

        originDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { _, t ->
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(applicationContext, "异常:${t.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CmdUtil.v("onDestroy")
        Thread.setDefaultUncaughtExceptionHandler(originDefaultHandler)
    }
}