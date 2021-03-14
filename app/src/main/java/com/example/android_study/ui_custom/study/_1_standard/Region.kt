package com.example.android_study.ui_custom.study._1_standard

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.Region
import android.graphics.RegionIterator
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import kotlin.random.Random


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class Region @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mOvalPaint: Paint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.RED
        strokeWidth = 2f
        isAntiAlias = true
    }

    private val mOvalPath = Path()
    private var mTempRect = Rect()
    private val mRegion = Region()
    private val mHafOvalRegion = Region(10, 50, 300, 350)

    //2
    private val mRectRegion = Region()
    private val mRect = Rect(0, 50, 250, 250)

    //3
    private var mPaint: Paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GREEN
        isAntiAlias = true
    }

    private val mVPath = Path().apply {
        addOval(0f, 50f, 200f, 150f, Path.Direction.CCW)
    }
    private val mHPath = Path().apply {
        addOval(50f, 0f, 150f, 200f, Path.Direction.CCW)
    }
    private val mRegionV = Region(0, 0, 200, 200)
    private val mRegionH = Region(0, 0, 200, 200)
    private val mFinalRegion = Region()


    override fun onDraw(canvas: Canvas) {
        //1
        mOvalPath.addOval(10f, 50f, 300f, 650f, Path.Direction.CCW)
        mRegion.setPath(mOvalPath, mHafOvalRegion)
        drawRegion(canvas, mRegion, mOvalPaint)
        //2 union
        canvas.translate(400f,0f)
        repeat(5) {
            mRectRegion.union(mRect.apply {
                left=Random.nextInt(0,300)
                top=Random.nextInt(0,20)
                right=Random.nextInt(100,500)
                bottom=Random.nextInt(100,200)
            })
        }

        drawRegion(canvas, mRectRegion, mOvalPaint)
        //3 op
        canvas.translate(-400f, 350f)
        canvas.drawPath(mVPath, mOvalPaint)
        canvas.drawPath(mHPath, mOvalPaint)
        mRegionV.setPath(mVPath, mRegionV)
        mRegionH.setPath(mHPath, mRegionH)
        mFinalRegion.op(mRegionV, mRegionH, Region.Op.INTERSECT)
        drawRegion(canvas, mFinalRegion, mPaint)

        canvas.translate(250f, 0f)
        canvas.drawPath(mVPath, mOvalPaint)
        canvas.drawPath(mHPath, mOvalPaint)
        mRegionV.setPath(mVPath, mRegionV)
        mRegionH.setPath(mHPath, mRegionH)
        mFinalRegion.op(mRegionV, mRegionH, Region.Op.DIFFERENCE)
        drawRegion(canvas, mFinalRegion, mPaint)

        canvas.translate(250f, 0f)
        canvas.drawPath(mVPath, mOvalPaint)
        canvas.drawPath(mHPath, mOvalPaint)
        mRegionV.setPath(mVPath, mRegionV)
        mRegionH.setPath(mHPath, mRegionH)
        mFinalRegion.op(mRegionV, mRegionH, Region.Op.REPLACE)
        drawRegion(canvas, mFinalRegion, mPaint)

        canvas.translate(250f, 0f)
        canvas.drawPath(mVPath, mOvalPaint)
        canvas.drawPath(mHPath, mOvalPaint)
        mRegionV.setPath(mVPath, mRegionV)
        mRegionH.setPath(mHPath, mRegionH)
        mFinalRegion.op(mRegionV, mRegionH, Region.Op.XOR)
        drawRegion(canvas, mFinalRegion, mPaint)
    }

    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        val iterator = RegionIterator(region)
        while (iterator.next(mTempRect)) {
            canvas.drawRect(mTempRect, paint)
        }
    }
}