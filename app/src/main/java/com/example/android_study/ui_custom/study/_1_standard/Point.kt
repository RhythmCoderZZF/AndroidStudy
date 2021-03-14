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
class Point @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 15f
        color = Color.RED
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        canvas.withTranslation(200f, 0f) {
            drawPoint(0f, 10f, mPaint)
            mPaint.color=Color.BLUE
            drawPoints(floatArrayOf(0f,30f,20f,30f,40f,30f),mPaint)
        }
    }
}