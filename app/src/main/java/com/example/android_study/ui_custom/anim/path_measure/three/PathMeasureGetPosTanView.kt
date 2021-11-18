package com.example.android_study.ui_custom.anim.path_measure.three

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.graphics.withTranslation
import com.example.android_study.R
import kotlin.math.atan2

/**
 * Author:create by RhythmCoder
 * Date:2021/2/20
 * Description:
 */
@RequiresApi(Build.VERSION_CODES.M)
class PathMeasureGetPosTanView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val textPaint = TextPaint().apply {
        textSize = 45f
        color = Color.BLACK
    }

    private val pathPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val bitmapPaint = Paint()

    //————————————————————————————————————————————————————————————————————————————————————————————
    private val circlePath = Path().apply {
        addCircle(300f, 300f, 300f, Path.Direction.CW)
    }
    private val ovalPath = Path().apply {
        arcTo(0f,0f,400f,800f,0f,120f,true)
    }
    private val circlePathMeasure = PathMeasure(circlePath, false)
    private val ovalPathMeasure = PathMeasure(ovalPath, false)

    private val desCirclePath = Path()
    private val anim = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 1000
        repeatCount = ValueAnimator.INFINITE
    }

    private var direct = true

    private var value = 0f
    private val pos = FloatArray(2)
    private val tan = FloatArray(2)
    private var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon_back_left_arrow_press)
    private val mMatrix = Matrix()

    init {
        anim.addUpdateListener {
            value = it.animatedValue as Float
            if (direct) {
                if (value < 0.99f) {
                    desCirclePath.reset()
                    circlePathMeasure.getSegment(0f, circlePathMeasure.length * value, desCirclePath, true)
                }
            } else {
                if (value < 0.99f) {
                    desCirclePath.reset()
                    circlePathMeasure.getSegment(circlePathMeasure.length * value, circlePathMeasure.length, desCirclePath, true)
                }
            }
            invalidate()
        }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
                direct = !direct
            }
        })

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        anim.cancel()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        anim.start()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        canvas.save()
        var t = """

1. 
        """.trimIndent()
        StaticLayout.Builder.obtain(t, 0, t.length, textPaint, width)
                .setLineSpacing(0f, 1f).build().draw(canvas)


        canvas.translate(300f, 400f)
        canvas.drawPath(desCirclePath, pathPaint)
        circlePathMeasure.getPosTan(circlePathMeasure.length * value, pos, tan)
        var degrees = (atan2(tan[1], tan[0]) * 180 / Math.PI).toFloat()
        mMatrix.reset()
        mMatrix.postRotate(degrees-180, bitmap.width / 2f, bitmap.height / 2f)
        mMatrix.postTranslate(pos[0] - bitmap.width / 2, pos[1] - bitmap.height / 2)
        canvas.drawBitmap(bitmap, mMatrix, bitmapPaint)

        canvas.drawTextOnPath("你好啊！！！",desCirclePath,0f,0f,textPaint)

        canvas.withTranslation(0f, 700f) {
            canvas.drawPath(ovalPath, pathPaint)

            ovalPathMeasure.getPosTan(0f, pos, tan)
            degrees = (atan2(tan[1], tan[0]) * 180 / Math.PI).toFloat()
            mMatrix.reset()
            mMatrix.postRotate(degrees, bitmap.width / 2f, bitmap.height / 2f)
            mMatrix.postTranslate(pos[0] - bitmap.width / 2, pos[1] - bitmap.height / 2)
            canvas.drawBitmap(bitmap, mMatrix, bitmapPaint)

            ovalPathMeasure.getPosTan(400f, pos, tan)
            degrees = (atan2(tan[1], tan[0]) * 180 / Math.PI).toFloat()
            mMatrix.reset()
            mMatrix.postRotate(degrees, bitmap.width / 2f, bitmap.height / 2f)
            mMatrix.postTranslate(pos[0] - bitmap.width / 2, pos[1] - bitmap.height / 2)
            canvas.drawBitmap(bitmap, mMatrix, bitmapPaint)


            ovalPathMeasure.getPosTan(300f, pos, tan)
            degrees = (atan2(tan[1], tan[0]) * 180 / Math.PI).toFloat()
            mMatrix.reset()
            mMatrix.postRotate(degrees, bitmap.width / 2f, bitmap.height / 2f)
            mMatrix.postTranslate(pos[0] - bitmap.width / 2, pos[1] - bitmap.height / 2)
            canvas.drawBitmap(bitmap, mMatrix, bitmapPaint)


        }
    }
}