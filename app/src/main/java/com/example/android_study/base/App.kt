package com.example.android_study.base

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.Choreographer
import com.example.android_study.base.cmd.CmdUtil
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

/**
 * @author quchao
 * @date 2017/11/27
 */
class App : Application() {
    private var mStartFrameTime: Long = 0
    private var mFrameCount = 0
    private var refWatcher: RefWatcher? = null
    override fun onCreate() {
        super.onCreate()
        fPS
        instance = this
        refWatcher = setupLeakCanary() // LeakCanary创建
    }

    /**
     * 设置setupLeakCanary
     *
     * @return
     */
    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    /**
     * 实时获取UI线程的fps
     */
    @get:TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private val fPS: Unit
        private get() {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                return
            }
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
                        if (fpsInt < 30) {
                            CmdUtil.fps(fps1, Color.RED)
                        } else if (fpsInt < 60) {
                            CmdUtil.fps(fps1)
                        } else {
                            CmdUtil.fps(fps1, Color.GREEN)
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
        fun getRefWatcher(context: Context): RefWatcher? {
            val leakApplication = context.applicationContext as App
            return leakApplication.refWatcher
        }
    }
}