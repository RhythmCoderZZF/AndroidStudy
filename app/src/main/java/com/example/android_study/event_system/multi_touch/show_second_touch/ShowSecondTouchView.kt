package com.example.android_study.event_system.multi_touch.show_second_touch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/8/30
 * Description:
 */
class ShowSecondTouchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val firstFinger = Point(-1, -1)
    private val secondFinger = Point(-1, -1)

    private val mFirstPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val mSecondPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    private val RADIO = 160f


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val index = event.actionIndex
        CmdUtil.i("Index:$index")
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                CmdUtil.v("Down")

                //1.第一个手指
                firstFinger.x = event.x.toInt()
                firstFinger.y = event.y.toInt()
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                CmdUtil.v("Pointer Down")
                //2.找到第二根手指
                if (event.getPointerId(index) == 1) {
                    secondFinger.x = event.getX(index).toInt()
                    secondFinger.y = event.getY(index).toInt()
                }
            }
            MotionEvent.ACTION_POINTER_UP -> {
                CmdUtil.v("Pointer UP")
                if (event.getPointerId(index) == 1) {
                    secondFinger.x = -1
                    secondFinger.y = -1
                }
            }
            MotionEvent.ACTION_MOVE -> {
                CmdUtil.v("Move")
            }
            MotionEvent.ACTION_UP -> {
                CmdUtil.v("Up")
                firstFinger.x = -1
                firstFinger.y = -1
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            if (firstFinger.x > 0 && firstFinger.y > 0) {
                drawCircle(firstFinger.x.toFloat(), firstFinger.y.toFloat(), RADIO, mFirstPaint)
            }
            if (secondFinger.x > 0 && secondFinger.y > 0) {
                drawCircle(
                    secondFinger.x.toFloat(),
                    secondFinger.y.toFloat(),
                    RADIO,
                    mSecondPaint
                )
            }
        }
    }
}