package com.example.android_study._base

import android.app.Application
import android.graphics.Color
import android.view.Choreographer
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil

/**
 * @author quchao
 * @date 2017/11/27
 */
class App : Application() {
    private var mStartFrameTime: Long = 0
    private var mFrameCount = 0

    override fun onCreate() {
        super.onCreate()
        val startTime=System.currentTimeMillis()
        initFPS()
        instance = this
        LogUtil.d("","${System.currentTimeMillis()-startTime}")
    }
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
    }
}