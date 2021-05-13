package com.example.android_study.ui_custom.anim.path_measure

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

/**
 * Author:create by RhythmCoder
 * Date:2021/2/20
 * Description:
 */
@RequiresApi(Build.VERSION_CODES.M)
class PathMeasureGetSegmentView @JvmOverloads constructor(
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

    //————————————————————————————————————————————————————————————————————————————————————————————
    private val circlePath = Path().apply {
        addCircle(150f, 150f, 150f, Path.Direction.CCW)
    }
    private val ovalPath = Path().apply {
        addOval(0f, 0f, 200f, 120f, Path.Direction.CW)
    }
    private val circleSegmentPath = PathMeasure(circlePath, false)
    private val ovalSegmentPath = PathMeasure(ovalPath, false)
    private val desPath = Path()
    private val desPath1 = Path()
    private val anim = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 1000
        repeatCount = ValueAnimator.INFINITE
    }

    private var direct = true

    init {
        anim.addUpdateListener {
            val value = it.animatedValue as Float
            if (direct) {
                if (value < 0.99f) {
                    desPath.reset()
                    circleSegmentPath.getSegment(0f, circleSegmentPath.length * value, desPath, true)
                }
            } else {
                if (value < 0.99f) {
                    desPath.reset()
                    circleSegmentPath.getSegment(circleSegmentPath.length * value, circleSegmentPath.length, desPath, true)
                }
            }
            //——————————————————————————————————————————————————————————————————————————————
            desPath1.reset()
            if (value < 0.5) {
                ovalSegmentPath.getSegment(0f, ovalSegmentPath.length * value, desPath1, true)
            } else {
                ovalSegmentPath.getSegment(2f * (value - 0.5f) * ovalSegmentPath.length * value, ovalSegmentPath.length * value, desPath1, true)
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
        anim.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        anim.cancel()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        canvas.save()
        var t = """
 
        """.trimIndent()
        StaticLayout.Builder.obtain(t, 0, t.length, textPaint, width)
                .setLineSpacing(0f, 1f).build().draw(canvas)


        //2. 第二部分【PathMeasure.getSegment()路径截取的用法】————————————————————————————————————————————————————————————————————————————————————————————
        canvas.translate(0f, 500f)
        canvas.drawPath(desPath, pathPaint)
        canvas.withTranslation(400f, 0f) {
            canvas.drawPath(desPath1, pathPaint)
        }
    }
}