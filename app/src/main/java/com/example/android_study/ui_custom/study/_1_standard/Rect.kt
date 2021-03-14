package com.example.android_study.ui_custom.study._1_standard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Author:create by RhythmCoder
 * Date:2021/2/2
 * Description:
 */
class Rect@JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mX = -1f
    private var mY = -1f
    private val mPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 1f
    }

    private val mRect = RectF(100f, 100f, 500f, 300f)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mX = event.x
                mY = event.y
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                mX = -1f
                mY = -1f
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        if (mRect.contains(mX, mY)) {
            canvas.drawRoundRect(mRect, 10f, 10f, mPaint.apply {
                style = Paint.Style.FILL
            })
        } else {
            canvas.drawRoundRect(mRect, 10f, 10f, mPaint.apply {
                style = Paint.Style.STROKE
            })
        }

    }
}