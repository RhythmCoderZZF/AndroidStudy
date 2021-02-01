package com.example.android_study._base

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.Choreographer
import com.example.android_study._base.cmd.CmdUtil
import com.tencent.bugly.crashreport.CrashReport
//import com.squareup.leakcanary.LeakCanary
//import com.squareup.leakcanary.RefWatcher
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import io.rong.imkit.RongIM
import leakcanary.LeakCanary

/**
 * @author quchao
 * @date 2017/11/27
 */
class App : Application() {
    private var mStartFrameTime: Long = 0
    private var mFrameCount = 0
//    private var refWatcher: RefWatcher? = null

    //融云
    private val appKey = "k51hidwqkvwxb"
    private val umAppKey = "5f7292b280455950e49ac631"
    private val umAppSecret = "4f1bd38eef9ae80f79d130fb3ba9b7a2"
    override fun onCreate() {
        super.onCreate()
        initFPS()
        instance = this
//        refWatcher = setupLeakCanary() // LeakCanary创建

        RongIM.init(this, appKey, true)//融云
        initUmengSDK()

        //bugly
        CrashReport.initCrashReport(this, "36fdb1285e", true);
    }

//    /**
//     * 设置setupLeakCanary
//     *
//     * @return
//     */
//    private fun setupLeakCanary(): RefWatcher {
//        return if (LeakCanary.isInAnalyzerProcess(this)) {
//            RefWatcher.DISABLED
//        } else LeakCanary.install(this)
//    }

    /**
     * 实时获取UI线程的fps
     */
    private fun initFPS() {
        Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
            override fun doFrame(frameTimeNanos: Long) {
                if (mStartFrameTime == 0L) {
                    mStartFrameTime = frameTimeNanos
                }
                val interval = frameTimeNanos - mStartFrameTime
                if (interval > MONITOR_INTERVAL_NANOS) {
                    val fps = (mFrameCount * 1000L * 1000L).toDouble() / interval * MAX_INTERVAL
                    val fpsInt = java.lang.Double.valueOf(fps).toInt()
                    val fps1 = java.lang.Double.valueOf(fps).toInt().toString()
                    when {
                        fpsInt < 30 -> {
                            CmdUtil.fps(fps1, Color.RED)
                        }
                        fpsInt < 60 -> {
                            CmdUtil.fps(fps1)
                        }
                        else -> {
                            CmdUtil.fps(fps1, Color.GREEN)
                        }
                    }
                    mFrameCount = 0
                    mStartFrameTime = 0
                } else {
                    ++mFrameCount
                }
                Choreographer.getInstance().postFrameCallback(this)
            }
        })
    }


    private fun initUmengSDK() {
        UMConfigure.setLogEnabled(true)
        UMConfigure.init(instance, umAppKey, "umeng", UMConfigure.DEVICE_TYPE_PHONE,
                umAppSecret)
        PushAgent.getInstance(this).register(object : IUmengRegisterCallback {
            override fun onSuccess(s: String) {
                Log.i("walle", "--->>> onSuccess, s is $s")
            }

            override fun onFailure(s: String, s1: String) {
                Log.i("walle", "--->>> onFailure, s is $s, s1 is $s1")
            }
        })
    }

    companion object {
        lateinit var instance: App

        /**
         * 单次计算FPS使用160毫秒
         */
        private const val MONITOR_INTERVAL = 160L
        private const val MONITOR_INTERVAL_NANOS = MONITOR_INTERVAL * 1000L * 1000L

        /**
         * 设置计算fps的单位时间间隔1000ms,即fps/s;
         */
        private const val MAX_INTERVAL = 1000L

        /**
         * @param context
         * @return
         */
//        fun getRefWatcher(context: Context): RefWatcher? {
//            val leakApplication = context.applicationContext as App
//            return leakApplication.refWatcher
//        }
    }
}