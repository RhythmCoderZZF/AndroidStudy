package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.android_study._base.util.dp2px


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class VText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val  mStartPosition=500f

    private val mPaintLine = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        color=Color.RED
    }


    private val mPaint = Paint().apply {
        //基础
        strokeWidth = 2f
        isAntiAlias = true
        style = Paint.Style.STROKE
        textSize = 80f

        //样式
        isFakeBoldText = true//加粗
        isUnderlineText = true//下划线
        isStrikeThruText = true//删除线

        //其他
        textSkewX = 0.25f
        textScaleX = 2f
    }


    override fun onDraw(canvas: Canvas) {

        canvas.drawLine(mStartPosition, 0f, mStartPosition, 1000f, mPaintLine)

        canvas.translate(0f,200f)
        canvas.drawText("哈Aa", mStartPosition, 50f, mPaint)

        canvas.translate(0f,100f)
        canvas.drawText("哈Aa", mStartPosition, 100f, mPaint.apply {
            style=Paint.Style.FILL
            textAlign=Paint.Align.CENTER
        })

        canvas.translate(0f,100f)
        canvas.drawText("哈Aa", mStartPosition, 150f, mPaint.apply {
            style = Paint.Style.FILL_AND_STROKE
            textSkewX = -0.5f
            textScaleX=1f
            textAlign = Paint.Align.RIGHT
        })
    }
}