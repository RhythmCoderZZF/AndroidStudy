package com.example.android_study.ui_custom.event_nest.nest

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.OverScroller
import androidx.core.math.MathUtils
import androidx.core.view.NestedScrollingParent3
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import java.lang.Math.abs

/**
 * Author:create by RhythmCoder
 * Date:2021/5/24
 * Description:
 */
class SuspendedLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs),
    NestedScrollingParent3 {
    private val mParentHelper: NestedScrollingParentHelper = NestedScrollingParentHelper(this)
    private lateinit var mViewDragHelper: ViewDragHelper
    private lateinit var mScroller: OverScroller
    private var mLastComputeValue = 0

    init {
        mScroller = OverScroller(getContext())
        mViewDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int) =
                child.id != R.id.nestScrollView

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                scrollBy(0, -dy)
                return child.top
            }


            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                CmdUtil.i("release:$scrollY|$yvel")
                if (abs(yvel) > 0) {
                    mLastComputeValue = scrollY
                    mScroller.fling(0, scrollY, 0, yvel.toInt(), 0, 0, Int.MIN_VALUE, Int.MAX_VALUE)
                    postInvalidateOnAnimation()
                }
            }
        })
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                if (!mScroller.isFinished) {
                    mScroller.abortAnimation()
                }
            }
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mViewDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            CmdUtil.e("computeScroll:${mScroller.currY}")
            val trend = mScroller.currY - mLastComputeValue
            mLastComputeValue = mScroller.currY
            scrollTo(0, scrollY - trend)
            postInvalidateOnAnimation()
        }
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        onNestedScrollInternal(dyUnconsumed, type, consumed)
    }

    private fun onNestedScrollInternal(dyUnconsumed: Int, type: Int, consumed: IntArray?) {
        if (dyUnconsumed < 0) scrollDown(dyUnconsumed, consumed)
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mParentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        onNestedScrollInternal(dyUnconsumed, type, null)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (dy > 0) scrollUp(dy, consumed)
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        return onStartNestedScroll(child, target, nestedScrollAxes, ViewCompat.TYPE_TOUCH)
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        onNestedScrollAccepted(child, target, nestedScrollAxes, ViewCompat.TYPE_TOUCH)
    }

    override fun onStopNestedScroll(target: View) {
        onStopNestedScroll(target, ViewCompat.TYPE_TOUCH)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        onNestedScrollInternal(dyUnconsumed, ViewCompat.TYPE_TOUCH, null)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        onNestedPreScroll(target, dx, dy, consumed, ViewCompat.TYPE_TOUCH)
    }

    override fun getNestedScrollAxes(): Int {
        return mParentHelper.nestedScrollAxes
    }

    /*-------------------------------------------------*/

    private fun scrollDown(dyUnconsumed: Int, consumed: IntArray?) {
        val oldScrollY = scrollY
        scrollBy(0, dyUnconsumed)
        val myConsumed = scrollY - oldScrollY

        if (consumed != null) {
            consumed[1] += myConsumed
        }
    }

    private fun scrollUp(dy: Int, consumed: IntArray) {
        val oldScrollY = scrollY
        scrollBy(0, dy)
        consumed[1] = scrollY - oldScrollY
    }

    override fun scrollTo(x: Int, y: Int) {
        val validY = MathUtils.clamp(y, 0, headerHeight)
        if (validY == 0 || validY == headerHeight) {
            if (!mScroller.isFinished) {
                mScroller.abortAnimation()
            }
        }
        super.scrollTo(x, validY)
    }

    /*--------------------------------------------------------------------------------------------*/

    // 简单把第一个 child 作为 header
    private var headerHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (childCount > 0) {
            val headView = getChildAt(0)
            measureChildWithMargins(headView, widthMeasureSpec, 0, MeasureSpec.UNSPECIFIED, 0)
            headerHeight = headView.measuredHeight
            super.onMeasure(
                widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(heightMeasureSpec) + headerHeight,
                    MeasureSpec.EXACTLY
                )
            )
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}