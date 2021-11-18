package com.example.android_study.ui_custom.demo.demo_map

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.scale
import androidx.core.view.children
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import kotlin.math.abs

/**
 * Author:create by RhythmCoder
 * Date:2021/8/6
 * Description:
 */
class DemoMapView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    /* 1.隐藏路径 */
    private var mPreX = 0f
    private var mPreY = 0f
    private val mPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 10f
        isAntiAlias = true
    }
    val mMapPath = Path()

    /* 2.棋子 */
//    private var mBitmap_Flag = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
    private lateinit var mFlag: ImageView
    private lateinit var mFlagAnim: AnimationDrawable

    private lateinit var mPathMeasure: PathMeasure
    private val mSegmentPath1 = Path()
    private val mSegmentPath2 = Path()
    private val mSegmentPath3 = Path()

    private var currSegment = 0
    private val mAnim = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 2000
    }

    //当前时刻🚩的坐标与夹角
    private val mPos = FloatArray(2)
    private val mTan = FloatArray(2)

    /* 3.脚印 */
    private var mBitmap_Foot =
        BitmapFactory.decodeResource(resources, R.mipmap.seek_ico_circle_price_section).let {
            it.scale(40, 40)
        }

    //当前需要绘制脚印的坐标
    private val mFootCoordinates = mutableListOf<Pair<Float, Float>>()

    init {
        setWillNotDraw(false)
        mAnim.addUpdateListener {
            val value = it.animatedValue as Float
            if (currSegment == 0) {
                mPathMeasure.setPath(mSegmentPath1, false)
            }
            if (currSegment == 1) {
                mPathMeasure.setPath(mSegmentPath2, false)
            }
            if (currSegment == 2) {
                mPathMeasure.setPath(mSegmentPath3, false)
            }
            mPathMeasure.getPosTan(mPathMeasure.length * value, mPos, mTan)
            validateAndAddFootCoordinates()
            invalidate()
        }
        mAnim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                CmdUtil.i("start")
                mFlagAnim.start()
                if (currSegment > 2) {
                    currSegment = 0
                    mFootCoordinates.clear()
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
                CmdUtil.i("end")
                currSegment++
                mFlagAnim.stop()
            }

            override fun onAnimationCancel(animation: Animator?) {
                CmdUtil.i("cancel")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                CmdUtil.i("Repeat")
            }
        })
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (child in children) {
            when (child.id) {
                R.id.ivFlag -> {
                    mFlag = child as ImageView
                    mFlagAnim = child.drawable as AnimationDrawable
                }
            }
        }
    }

    fun segment() {
        if (!this::mPathMeasure.isInitialized) {
            mPathMeasure = PathMeasure(mMapPath, false)
            val length = mPathMeasure.length
            mPathMeasure.getSegment(0f, length / 3, mSegmentPath1, true)
            mPathMeasure.getSegment(length / 3, length / 3 * 2, mSegmentPath2, true)
            mPathMeasure.getSegment(length / 3 * 2, length, mSegmentPath3, true)
        }
        startRun()
    }

    private fun startRun() {
        mAnim.start()
    }

    private fun validateAndAddFootCoordinates() {
        val px = mPos[0]
        val py = mPos[1]
        mFlag.layout(
            px.toInt() - mFlag.width / 2,
            py.toInt() - mFlag.height / 2,
            px.toInt() + mFlag.width / 2,
            py.toInt() + mFlag.height / 28
        )

        if (mFootCoordinates.isEmpty()) {
            mFootCoordinates.add(Pair(px, py))
            return
        }
        val lastP = mFootCoordinates.last()
        val lx = lastP.first
        val ly = lastP.second
        if (abs(px - lx) > mBitmap_Foot.width || abs(py - ly) > mBitmap_Foot.height) {
            mFootCoordinates.add(Pair(px, py))
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                mMapPath.moveTo(event.x, event.y)
                mPreX = event.x
                mPreY = event.y
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                mMapPath.quadTo(mPreX, mPreY, (mPreX + event.x) / 2, (mPreY + event.y) / 2)
                mPreX = event.x
                mPreY = event.y
                invalidate()
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mMapPath, mPaint)

        /* 脚印 */
        repeat(mFootCoordinates.size) {
            canvas.drawBitmap(
                mBitmap_Foot,
                mFootCoordinates[it].first - mBitmap_Foot.width / 2,
                mFootCoordinates[it].second - mBitmap_Foot.height / 2,
                mPaint
            )
        }
//        canvas.drawBitmap(
//            mBitmap_Flag,
//            mPos[0] - mBitmap_Flag.width / 2,
//            mPos[1] - mBitmap_Flag.height / 2,
//            mPaint
//        )
    }

}