package com.example.android_study.ui_custom.demo.standard.spider_web

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.android_study._base.util.dp2px
import kotlin.math.cos
import kotlin.math.sin

/**
 * Author:create by RhythmCoder
 * Date:2021/2/7
 * Description:
 */
class SpiderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var centerX = 0
    private var centerY = 0
    private var maxRadius = 0f

    //数据参数
    private var mDataMax = 5
    private var polygonNum = 5
    private var polygonAngle = 360 / polygonNum
    var mData: Array<Int> = arrayOf(1, 2, 3, 4, 5)
        set(value) {
            field = value
            mDataMax = field.maxOrNull() ?: 0
            polygonNum = field.size
            polygonAngle = 360 / polygonNum
        }


    private val pathPolygon = Path()
    private val pathPoint = Path()
    private val mPaintPolygon = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.BLACK
        strokeWidth = 2f.dp2px().toFloat()
        isAntiAlias = true
    }
    private val mPaintDataRegoin = Paint().apply {
        style = Paint.Style.FILL
        color = Color.argb(117, 255, 0, 0)
        isAntiAlias = true
    }

    private val mPaintPoint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.argb(230, 150, 150, 100)
        isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        centerX = w / 2
        centerY = h / 2
        maxRadius = Math.min(w, h) / 2 * 0.9f
    }

    override fun onDraw(canvas: Canvas) {
        //1.边线
        drawPolygon(canvas)
        //2 对角线
        drawLine(canvas)
        //3 数据
        drawRegion(canvas)
    }

    private fun drawPolygon(canvas: Canvas) {
        repeat(polygonNum) {
            val eachRadius = maxRadius / polygonNum * it.plus(1)
            pathPolygon.reset()
            //每条边线的交点数
            repeat(polygonNum) { innerIndex ->
                if (innerIndex == 0) {
                    pathPolygon.moveTo(centerX.toFloat() + eachRadius, centerY.toFloat())
                } else {
                    val fx = (centerX + eachRadius * cos(Math.toRadians(polygonAngle.toDouble()) * innerIndex)).toFloat()
                    val fy = (centerY + eachRadius * sin(Math.toRadians(polygonAngle.toDouble()) * innerIndex)).toFloat()
                    pathPolygon.lineTo(fx, fy)
                }
            }
            pathPolygon.close()
            canvas.drawPath(pathPolygon, mPaintPolygon)
        }
    }

    private fun drawLine(canvas: Canvas) {

        repeat(polygonNum) {innerIndex->
            pathPolygon.reset()
            pathPolygon.moveTo(centerX.toFloat(), centerY.toFloat())
            val fx = (centerX + maxRadius  * cos(Math.toRadians(polygonAngle.toDouble()) * innerIndex)).toFloat()
            val fy = (centerY + maxRadius  * sin(Math.toRadians(polygonAngle.toDouble()) * innerIndex)).toFloat()
            pathPolygon.lineTo(fx,fy)
            canvas.drawPath(pathPolygon,mPaintPolygon)
        }
    }

    private fun drawRegion(canvas: Canvas) {
        pathPoint.reset()
        repeat(mData.size) { index ->
            val data = mData[index]
            val dataPercent = data / mDataMax.toFloat()
            val dataLength = dataPercent * maxRadius
            val x = centerX + dataLength * cos(Math.toRadians(polygonAngle.toDouble() * index)).toFloat()
            val y = centerY + dataLength * sin(Math.toRadians(polygonAngle.toDouble() * index)).toFloat()
            if (index == 0) {
                pathPoint.moveTo(x, y)
            } else {
                pathPoint.lineTo(x, y)
            }
            canvas.drawCircle(x, y, 8f.dp2px().toFloat(), mPaintPoint)
        }
        pathPoint.close()
        canvas.drawPath(pathPoint, mPaintDataRegoin)
    }
}