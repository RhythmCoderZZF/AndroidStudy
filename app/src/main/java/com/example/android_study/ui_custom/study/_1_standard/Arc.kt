package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class Arc @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth = 10f
        isAntiAlias = true
    }

    private var mPaint1: Paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
        strokeWidth = 10f
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {

        canvas.apply {
            drawArc(0f, 0f, 200f, 100f, 0f, 90f, false, mPaint)
            translate(100f,0f)
            drawArc(0f, 0f, 200f, 100f, 0f, 90f, false, mPaint)
            translate(120f,5f)
            drawArc(0f, 0f, 200f, 100f, 0f, -90f, true, mPaint1)
            translate(250f,0f)
            drawArc(0f, 0f, 200f, 100f, 0f, 360f, true, mPaint1)
        }

    }
}