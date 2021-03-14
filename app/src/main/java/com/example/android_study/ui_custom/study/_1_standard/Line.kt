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
class Line @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        color = Color.RED
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        canvas.withTranslation(200f, 0f) {
            //四个点一组
            drawLines(floatArrayOf(0f, 5f, 50f, 5f, 100f, 10f, 200f, 20f), mPaint)
            mPaint.color = Color.BLACK
            drawLines(floatArrayOf(0f, 5f, 10f, 5f, 100f, 150f, 200f, 250f), 1, 4, mPaint)
        }

    }
}