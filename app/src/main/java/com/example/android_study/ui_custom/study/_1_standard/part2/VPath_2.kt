package com.example.android_study.ui_custom.study._1_standard.part2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation

/**
 * Author:create by RhythmCoder
 * Date:2021/3/8
 * Description:
 */
class VPath_2 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mTextSize = 40f
    private var mEnd = 0f

    private val mTextPaint = TextPaint().apply {
        strokeWidth = 4f
        color = Color.BLACK
        textSize=mTextSize
    }
    private val mPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth=10f
        strokeJoin=Paint.Join.ROUND
        strokeCap= Paint.Cap.ROUND
    }

    private val mPath = Path().apply {
        quadTo(100f, -200f, 200f, 0f)
        quadTo(300f, -200f, 400f, 0f)
        rQuadTo(100f, -200f, 200f, 0f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.withTranslation(0f, 200f.apply { mEnd = this }) {
            canvas.drawPath(mPath, mPaint)
        }
        canvas.translate(0f, mEnd)
        canvas.drawText("二次贝塞尔曲线\n高级部分PathMeasure见动画篇", 0f, 0f, mTextPaint)

    }
}