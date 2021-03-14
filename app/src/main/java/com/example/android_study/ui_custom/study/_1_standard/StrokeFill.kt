package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class StrokeFill @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
        isAntiAlias = true
    }

    private var mPaint1: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.GREEN
        strokeWidth = 15f
        isAntiAlias = true
    }

    private var mPaint2: Paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLUE
        strokeWidth = 15f
        isAntiAlias = true
    }
    private var mPaintLine: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 1f
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(50f, 50f, 50f, mPaint)
        canvas.withTranslation(200f, 50f) {
            canvas.drawCircle(0f, 0f, 50f, mPaint1)
        }
        canvas.withTranslation(400f, 50f) {
            canvas.drawCircle(0f, 0f, 50f, mPaint2)
        }
        canvas.drawLine(0f, 100f, 800f, 100f, mPaintLine)
    }
}