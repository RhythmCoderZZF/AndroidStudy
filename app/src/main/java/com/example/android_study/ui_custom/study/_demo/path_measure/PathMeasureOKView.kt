package com.example.android_study.ui_custom.study._demo.path_measure

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.renderscript.Sampler
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.addListener
import androidx.core.graphics.withTranslation
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil
import com.example.android_study._base.util.dp2px

/**
 * Author:create by RhythmCoder
 * Date:2021/2/23
 * Description:
 */
class PathMeasureOKView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 4f.dp2px().toFloat()
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    private val segmentPath = Path()

    private val mOkPath = Path().apply {

        addCircle(100f, 100f, 100f, Path.Direction.CCW)
        moveTo(50f, 80f)
        lineTo(80f, 110f)
        lineTo(130f, 50f)

    }

    private val pathMeasure = PathMeasure(mOkPath, false)


    private val anim = ValueAnimator.ofFloat(0f, 1f, 2f).apply {
        duration = 2000
    }


    private var animValue = 0f
    private var once = true

    init {
        anim.addUpdateListener {
            animValue = it.animatedValue as Float
            CmdUtil.v("$animValue")
            CmdUtil.i("${it.animatedFraction}")
            if (animValue < 1f) {
                once = true
                segmentPath.reset()
                pathMeasure.getSegment(0f, pathMeasure.length * animValue, segmentPath, true)
            } else if (animValue > 1f) {
                if (once) {
                    once = false
                    pathMeasure.getSegment(0f, pathMeasure.length, segmentPath, true)
                    pathMeasure.nextContour()
                } else {
                    pathMeasure.getSegment(0f, pathMeasure.length * (animValue - 1f), segmentPath, true)
                }
            }
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        anim.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        anim.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.withTranslation(300f, 300f) {
            canvas.drawPath(segmentPath, mPaint)
        }
    }
}