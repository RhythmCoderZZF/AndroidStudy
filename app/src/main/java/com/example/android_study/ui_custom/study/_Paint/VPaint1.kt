package com.example.android_study.ui_custom.study._Paint

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.android_study.R
import com.example.android_study._base.util.dp2px


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class VPaint1 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mTextSize = 50f
    private var mTextSizeMove = mTextSize + 30f
    private var textPaint = TextPaint().apply {
        textSize = mTextSize
    }

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        isAntiAlias = true
    }

    /* pathEffect */
    private val path = Path().apply {
        lineTo(150f, 0f)
        lineTo(150f, 100f)
    }

    /* strokeCap */
    private val mPaint1 = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 20f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText("1.PathEffect", 0f, mTextSize, textPaint)
        canvas.translate(0f, mTextSizeMove)
        canvas.drawPath(path, mPaint.apply {
            pathEffect = CornerPathEffect(10f.dp2px().toFloat())
        })

        canvas.translate(0f, 180f)
        canvas.drawText("2.StrokeCap(以下依次是ROUND|SQUARE|BUTT)", 0f, mTextSize, textPaint)
        canvas.translate(0f, mTextSizeMove)
        canvas.withTranslation(50f, 0f) {
            canvas.drawLine(0f, 0f, 0f, 200f, mPaint)

            canvas.drawLine(0f, 0f, 200f, 0f, mPaint1.apply {
                strokeCap = Paint.Cap.ROUND
            })

            canvas.translate(0f, 60f)
            canvas.drawLine(0f, 0f, 200f, 0f, mPaint1.apply {
                strokeCap = Paint.Cap.SQUARE
            })

            canvas.translate(0f, 60f)
            canvas.drawLine(0f, 0f, 200f, 0f, mPaint1.apply {
                strokeCap = Paint.Cap.BUTT
            })

        }

        canvas.translate(0f, 250f)
        canvas.drawText("3.StrokeJoin(以下依次是MITER|BEVEL|ROUND)", 0f, mTextSize, textPaint)
        canvas.translate(0f, mTextSizeMove)
        canvas.drawPath(path, mPaint1.apply {
            strokeJoin = Paint.Join.MITER
        })


        canvas.withTranslation(200f, 0f) {
            canvas.drawPath(path, mPaint1.apply {
                strokeJoin = Paint.Join.BEVEL
            })
        }

        canvas.withTranslation(400f, 0f) {
            canvas.drawPath(path, mPaint1.apply {
                strokeJoin = Paint.Join.ROUND
                isDither
            })
        }
    }
}