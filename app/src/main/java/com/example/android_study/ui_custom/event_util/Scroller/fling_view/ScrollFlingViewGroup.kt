package com.example.android_study.ui_custom.event_util.Scroller.fling_view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.Scroller
import androidx.core.view.ViewCompat
import java.lang.Math.abs

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ScrollFlingViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var downX = 0f
    private var downY = 0f
    private var lastX = downX
    private var lastY = downY
    private var mScroller = Scroller(context)
    private val mMinimumFlingVelocity =
        ViewConfiguration.get(context).scaledMinimumFlingVelocity
    private val mMaxFlingVelocity =
        ViewConfiguration.get(context).scaledMaximumFlingVelocity
    private var mVelocityTracker: VelocityTracker? = null

    override fun onTouchEvent(e: MotionEvent)
            : Boolean {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker?.addMovement(e)
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = e.x
                downY = e.y
                lastX = downX
                lastY = downY
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (e.x - lastX).toInt()
                val y = (e.y - lastY).toInt()
                ViewCompat.offsetLeftAndRight(getChildAt(0), x)
                ViewCompat.offsetTopAndBottom(getChildAt(0), y)
                lastX = e.x
                lastY = e.y
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                //计算当前滑动速率，该方法性能消耗很大，参数单位为毫秒
                mVelocityTracker?.computeCurrentVelocity(1000, mMaxFlingVelocity.toFloat())
                //获取计算后得到的X轴滑动速率
                val initVelocityX = mVelocityTracker!!.xVelocity.toInt()
                val initVelocityY = mVelocityTracker!!.yVelocity.toInt()

                if (abs(initVelocityX) > mMinimumFlingVelocity || abs(initVelocityY) > mMinimumFlingVelocity) {
                    mScroller.fling(
                        getChildAt(0).left,
                        getChildAt(0).top,
                        initVelocityX,
                        initVelocityY,
                        0,
                        width - getChildAt(0).width,
                        0,
                        height - getChildAt(0).height
                    )
                }
                invalidate()
                if (mVelocityTracker != null) {
                    mVelocityTracker!!.recycle()
                    mVelocityTracker = null
                }
            }
        }
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            ViewCompat.offsetLeftAndRight(getChildAt(0), mScroller.currX - getChildAt(0).left)
            ViewCompat.offsetTopAndBottom(getChildAt(0), mScroller.currY - getChildAt(0).top)
            invalidate()
        }
    }
}