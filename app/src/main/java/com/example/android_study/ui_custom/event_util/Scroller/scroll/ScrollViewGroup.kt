package com.example.android_study.ui_custom.event_util.Scroller.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Scroller

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ScrollViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var downX = 0f
    private var downY = 0f
    private var lastX = downX
    private var lastY = downY
    private var mScroller = Scroller(context)

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = e.x
                downY = e.y
                lastX = downX
                lastY = downY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = (e.x - lastX).toInt()
                val y = (e.y - lastY).toInt()
                scrollBy(-x, -y)
                lastX = e.x
                lastY = e.y
            }
            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                mScroller.startScroll(scrollX, scrollY, -scrollX, -scrollY)
                invalidate()
            }
        }
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }

}