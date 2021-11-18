package com.example.android_study.event_system.event_util.Scroller.fling

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.Scroller
import com.example.android_study._base.cmd.CmdUtil
import java.lang.Math.abs

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ScrollFlingViewGroup1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var downY = 0f
    private var lastY = downY
    private var mScroller = Scroller(context)
    private val mMinimumFlingVelocity =
        ViewConfiguration.get(context).scaledMinimumFlingVelocity
    private val mMaxFlingVelocity =
        ViewConfiguration.get(context).scaledMaximumFlingVelocity
    private var mVelocityTracker: VelocityTracker? = null

    private var mLastComputeValue = 0

    override fun onTouchEvent(e: MotionEvent)
            : Boolean {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
        mVelocityTracker?.addMovement(e)
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                downY = e.y
                lastY = downY
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
                CmdUtil.i("$scrollY")
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val y = (e.y - lastY).toInt()
                scrollBy(0, -y)
                lastY = e.y
                CmdUtil.i("$scrollY")
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                //计算当前滑动速率，该方法性能消耗很大，参数单位为毫秒
                mVelocityTracker?.computeCurrentVelocity(1000, mMaxFlingVelocity.toFloat())
                //获取计算后得到的X轴滑动速率
                val initVelocityY = mVelocityTracker!!.yVelocity.toInt()
                mLastComputeValue = scrollY
                if (abs(initVelocityY) > mMinimumFlingVelocity) {
                    mScroller.fling(
                        0,
                        scrollY,
                        0,
                        initVelocityY,
                        0,
                        0,
                        Int.MIN_VALUE,
                        Int.MAX_VALUE
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
            val trend = mScroller.currY - mLastComputeValue
            scrollTo(0, scrollY - trend)
            CmdUtil.v("$scrollY")
            invalidate()
            mLastComputeValue = mScroller.currY
        }
    }
}