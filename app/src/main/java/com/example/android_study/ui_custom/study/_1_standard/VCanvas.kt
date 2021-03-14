package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.android_study._base.util.dp2px


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class VCanvas @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mTextSize = 50f
    private var mTextSizeMove = mTextSize + 40f
    private var textPaint = TextPaint().apply {
        textSize = mTextSize
    }

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        isAntiAlias = true
    }


    /* 二阶贝塞尔 */
    private val pathQuad = Path().apply {
        moveTo(0f, 100f)
        quadTo(100f, 0f, 200f, 100f)
        quadTo(300f, 300f, 400f, 150f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.translate(0f, mTextSizeMove)
        canvas.drawText("二阶贝塞尔",0f,mTextSize,textPaint)
        canvas.drawPath(pathQuad, mPaint)

    }
}