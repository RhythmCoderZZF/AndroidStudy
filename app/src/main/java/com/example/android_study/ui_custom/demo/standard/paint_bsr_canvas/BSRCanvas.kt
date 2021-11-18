package com.example.android_study.ui_custom.demo.standard.paint_bsr_canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Author:create by RhythmCoder
 * Date:2021/2/26
 * Description:
 */
class BSRCanvas @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPreX = 0f
    private var mPreY = 0f
    private val mPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 10f
        isAntiAlias=true
    }


     val mPath = Path()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                mPath.moveTo(event.x, event.y)
                mPreX=event.x
                mPreY=event.y
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                mPath.quadTo(mPreX, mPreY, (mPreX+event.x) / 2, (mPreY+event.y) / 2)
                mPreX = event.x
                mPreY = event.y
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath,mPaint)
    }

}