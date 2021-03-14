package com.example.android_study.ui_custom.study._SurfaceView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Author:create by RhythmCoder
 * Date:2021/3/6
 * Description:
 */
class _LockCanvasSurfaceView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    private val mPaint = Paint().apply {
        color = Color.RED
        textSize = 60f
    }

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder?) {
                drawText(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }
        })
    }

    private fun drawText(holder: SurfaceHolder?) {
        holder?.let {
            repeat(10) {
                val c = holder.lockCanvas()
                c.drawText("$it", 20f * (it + 1), 60f, mPaint)
                holder.unlockCanvasAndPost(c)
            }
        }
    }


}