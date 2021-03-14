package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withClip


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class Path_FillType @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
        isAntiAlias = true
    }
    private var mPath_W = Path()
    private var mPath_O = Path()
    private var mPath_IW = Path()
    private var mPath_IO = Path()


    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            translate(20f, 20f)
            drawPath(mPath_W.apply {
                reset()
                addRect(0f, 0f, 100f, 100f, Path.Direction.CCW)
                addCircle(100f, 100f, 50f, Path.Direction.CCW)
                fillType = Path.FillType.WINDING
            }, mPaint)

            translate(200f, 0f)
            drawPath(mPath_O.apply {
                reset()
                addRect(0f, 0f, 100f, 100f, Path.Direction.CCW)
                addCircle(100f, 100f, 50f, Path.Direction.CCW)
                fillType = Path.FillType.EVEN_ODD
            }, mPaint)

            translate(200f, 0f)
            canvas.withClip(0, 0, 150, 150) {
                drawPath(mPath_IW.apply {
                    reset()
                    addRect(0f, 0f, 100f, 100f, Path.Direction.CCW)
                    addCircle(100f, 100f, 50f, Path.Direction.CCW)
                    fillType = Path.FillType.INVERSE_WINDING
                }, mPaint)
            }

            translate(200f, 0f)
            canvas.clipRect(0, 0, 150, 150)
            drawPath(mPath_IO.apply {
                reset()
                addRect(0f, 0f, 100f, 100f, Path.Direction.CCW)
                addCircle(100f, 100f, 50f, Path.Direction.CCW)
                fillType = Path.FillType.INVERSE_EVEN_ODD
            }, mPaint)
        }
    }
}