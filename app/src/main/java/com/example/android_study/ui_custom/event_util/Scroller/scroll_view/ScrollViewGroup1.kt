package com.example.android_study.ui_custom.event_util.Scroller.scroll_view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Scroller
import androidx.core.view.ViewCompat

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ScrollViewGroup1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var downX = 0f
    private var downY = 0f
    private var lastX = downX
    private var lastY = downY
    private var mScroller = Scroller(context)
    private var mCapturedChild: View? = null

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = e.x
                downY = e.y
                mCapturedChild = findTopChildUnder(e.x.toInt(), e.y.toInt())
                lastX = downX
                lastY = downY
                return mCapturedChild != null
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (e.x - lastX).toInt()
                val y = (e.y - lastY).toInt()
                mCapturedChild?.let {
                    ViewCompat.offsetLeftAndRight(it, x)
                    ViewCompat.offsetTopAndBottom(it, y)
                }
                lastX = e.x
                lastY = e.y
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                mCapturedChild?.let {
                    val left = it.left
                    val top = it.top
                    val finalX = 200
                    val finalY = 500
                    mScroller.startScroll(left, top, finalX - left, finalY - top)
                    invalidate()
                }
            }
        }
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        super.computeScroll()
        mCapturedChild?.let {
            if (mScroller.computeScrollOffset()) {
                val left=it.left
                val top=it.top
                val currDx = mScroller.currX
                val currDY = mScroller.currY
                ViewCompat.offsetLeftAndRight(it, currDx - left)
                ViewCompat.offsetTopAndBottom(it, currDY - top)
                invalidate()
            }
        }
    }

    fun findTopChildUnder(x: Int, y: Int): View? {
        val childCount: Int = childCount
        for (i in childCount - 1 downTo 0) {
            val child: View = getChildAt(i)
            if (x >= child.left && x < child.right && y >= child.top && y < child.bottom) {
                return child
            }
        }
        return null
    }
}