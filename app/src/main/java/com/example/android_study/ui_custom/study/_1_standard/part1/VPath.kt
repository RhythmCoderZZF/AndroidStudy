package com.example.android_study.ui_custom.study._1_standard.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.graphics.Paint
import android.graphics.Path
import androidx.annotation.RequiresApi


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class VPath @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth = 5f
        isAntiAlias = true
    }
    private val mPathLine = Path().apply {
        moveTo(10f, 10f)
        lineTo(200f, 100f)
        lineTo(10f, 100f)
        close()
    }


    private val mPathArc = Path()
    private val mPathArc1 = Path()
    private val mPathAddCircle = Path()
    private val mPathAddRoundRect = Path()

    private val mTextPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3f
        textSize = 40f
        color = Color.BLACK
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onDraw(canvas: Canvas) {
        //1 普通路径
        canvas.apply {
            save()
            //line
            drawPath(mPathLine, mPaint)
            translate(200f, 0f)
            //arc
            mPathArc.apply {
                moveTo(20f, 0f)
                arcTo(0f, 0f, 100f, 50f, 0f, 90f, false)//前4：边距 后2：起始角度和扫过角度
            }
            drawPath(mPathArc, mPaint)

            translate(200f, 0f)
            mPathArc1.apply {
                moveTo(20f, 0f)
                arcTo(0f, 0f, 100f, 50f, 0f, 90f, true)
            }
            drawPath(mPathArc1, mPaint)
        }

        //2 addXXX添加路径
        canvas.apply {
            restore()
            save()
            translate(20f, 110f)

            //addCircle CCW
            drawPath(mPathAddCircle.apply {
                reset()
                addCircle(100f, 100f, 90f, Path.Direction.CCW)
            }, mPaint)

            //drawText CCW
            drawTextOnPath("addXXX是有方向的哦",
                    mPathAddCircle, 0f, 0f,//
                    mTextPaint)

            translate(200f, 0f)
            //addCircle CW
            drawPath(mPathAddCircle.apply {
                reset()
                addCircle(100f, 100f, 90f, Path.Direction.CW)
            }, mPaint)

            //drawText CW
            drawTextOnPath("addXXX是有方向的哦", mPathAddCircle, 0f, 0f, mTextPaint.apply {
                style = Paint.Style.FILL
            })

            //roundRect
            translate(200f, 0f)
            drawPath(mPathAddRoundRect.apply {
                reset()
                addRoundRect(RectF(0f, 0f, 200f, 100f), 30f, 50f, Path.Direction.CW)
            }, mPaint)
            translate(210f, 0f)
            drawPath(mPathAddRoundRect.apply {
                reset()
                addRoundRect(RectF(0f, 0f, 200f, 100f),
                        floatArrayOf(20f, 20f, 30f, 50f, 10f, 10f, 50f, 30f),//8个4组，分别是左上，右上，右下，左下的椭圆横竖半径
                        Path.Direction.CCW)
            }, mPaint)
        }
    }
}