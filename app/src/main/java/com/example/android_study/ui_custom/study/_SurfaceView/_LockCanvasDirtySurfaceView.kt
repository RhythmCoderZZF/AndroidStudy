package com.example.android_study.ui_custom.study._SurfaceView

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.concurrent.thread

/**
 * Author:create by RhythmCoder
 * Date:2021/3/6
 * Description:
 */
class _LockCanvasDirtySurfaceView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    private val mPaint = Paint().apply {
        color = Color.RED
        textSize = 60f
    }
    private val scope = MainScope()

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                scope.launch(IO) {
                    drawText(holder)
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                scope.cancel()
            }
        })
    }

    private suspend fun drawText(holder: SurfaceHolder?) {
        holder?.let {
            clearSurface(holder)
            val tranDis = 60
            repeat(10) { index ->
                val c = holder.lockCanvas(Rect(index * tranDis + 10, 20, index * tranDis + 60, 80))
                c.drawColor(Color.BLUE)
                c.drawText("$index", index * tranDis + 10f, 70f, mPaint)
                holder.unlockCanvasAndPost(c)
                delay(1000)
            }
        }
    }

    private fun clearSurface(holder: SurfaceHolder) {
        while (true) {
            val canvas = holder.lockCanvas(Rect(0, 0, 1, 1))
            if (width == canvas.clipBounds.width() && height == canvas.clipBounds.height()) {
                canvas.drawPaint(Paint().apply {
                    xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                })
                holder.unlockCanvasAndPost(canvas)
            } else {
                holder.unlockCanvasAndPost(canvas)
                break
            }
        }
    }
}