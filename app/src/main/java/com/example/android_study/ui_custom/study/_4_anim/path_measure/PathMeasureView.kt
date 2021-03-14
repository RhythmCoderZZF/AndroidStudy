package com.example.android_study.ui_custom.study._4_anim.path_measure

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
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/2/20
 * Description:
 */
@RequiresApi(Build.VERSION_CODES.M)
class PathMeasureView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val textPaint = TextPaint().apply {
        textSize = 45f
        color = Color.BLACK
    }

    private val pathPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }
    private val segmentPaint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }
    /*——————————————————————————————————————————————————————————————————————————*/
    private val rectPath = Path().apply {
        addRect(0f, 0f, 200f, 200f, Path.Direction.CW)
    }

    private val segmentPath = Path().apply {
//        lineTo(80f, 200f)
        PathMeasure(rectPath, false).getSegment(210f, 300f, this, true)
        PathMeasure(rectPath, false).getSegment(400f, 500f, this, true)
    }

    private val segmentPath1 = Path().apply {
//        lineTo(80f, 200f)
        PathMeasure(rectPath, false).getSegment(210f, 300f, this, false)
        PathMeasure(rectPath, false).getSegment(400f, 500f, this, false)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas) {
        canvas.save()

        //1. 第一部分————————————————————————————————————————————————————————————————————————————————————————
        canvas.translate(50f, 50f)
        /* rect */
        canvas.drawPath(rectPath, pathPaint)

        canvas.translate(300f, 0f)
        /* segmentPath */
        canvas.drawPath(rectPath, pathPaint)
        canvas.drawPath(segmentPath, segmentPaint)

        canvas.translate(300f, 0f)
        /* segmentPath1 */
        canvas.drawPath(rectPath, pathPaint)
        canvas.drawPath(segmentPath1, segmentPaint)

        canvas.restore()
        canvas.translate(0f, 300f)

        var t = """
1. PathMeasure用于对Path的路径追踪，分割等计算，用于矢量动画
2. PathMeasure(rectPath,false)第二个参数forceClose=true时，PathMeasure计算会多加上最后一段闭合路径，但不改变内部的Path
3. getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)函数
startD：距离Path起始点的位置
stopD：距离Path终点的位置
dst：截取后的Path**添加**到dst（如果startD==stopD或者它们都不在【0~PathMeasure.getLength】范围内该函数返回false，且不改变dst）
startWithMoveTo：截取的Path起始点是否调用moveTo函数。fasle——表示不调用moveTo，则起始点会和dst中上一个Path片段的终点连接，保证连续性；true——保证每个截取的Path片段的独立性（一般为true）
        """.trimIndent()
        StaticLayout.Builder.obtain(t, 0, t.length, textPaint, width)
                .setLineSpacing(0f, 1f).build().draw(canvas)

    }
}