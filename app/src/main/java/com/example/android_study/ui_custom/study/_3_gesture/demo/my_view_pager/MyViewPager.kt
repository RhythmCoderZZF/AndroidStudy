package com.example.android_study.ui_custom.study._3_gesture.demo.my_view_pager

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/12
 * Description:
 * 1. 利用myGestureCallback实现MyViewPager的scroll
 * 2. 自定义MyScroll松手回弹效果
 */
class MyViewPager @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var startPressX = 0f
    private var endUpX = 0f

    private var mCurrPosition = 0

    private var dontScroll = true

    private val myGestureCallback = object : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            if (!dontScroll) {
                CmdUtil.e("e1:${e1?.x}|${e1?.y} e2:${e2?.x}|${e2?.y}")
                CmdUtil.e("scroll:${distanceX.toInt()}")
                scrollBy(distanceX.toInt(), 0)
            }
            return super.onScroll(e1, e2, distanceX, distanceY)
        }
    }
    private val mGesture = GestureDetector(context, myGestureCallback).apply {
        setIsLongpressEnabled(false)
        isClickable = false
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        mGesture.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                CmdUtil.i("O I:DOWN")
                startPressX = event.x
                dontScroll=true
            }
            MotionEvent.ACTION_MOVE -> {
                CmdUtil.i("O I:MOVE")
                if (Math.abs(event.x - startPressX) > 200) {
                    dontScroll = false
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                CmdUtil.i("O I:UP")
            }
            MotionEvent.ACTION_CANCEL -> {
                CmdUtil.i("O I:CANCEL")
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        mGesture.onTouchEvent(event)
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                CmdUtil.v("O:DOWN")
                startPressX = x
                return true
            }
            MotionEvent.ACTION_UP -> {
                CmdUtil.v("O:UP")
                endUpX = x
                scrollToPage(startPressX.toInt(), endUpX.toInt())
            }
            MotionEvent.ACTION_CANCEL -> {
                CmdUtil.v("O:CANCEL")
            }
            MotionEvent.ACTION_MOVE -> {
                CmdUtil.v("O:MOVE")
                return false
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        repeat(childCount) { index ->
            val child = getChildAt(index)
            child.layout(index * width, t, index * width + child.measuredWidth, child.measuredHeight)
        }
    }

    private fun scrollToPage(startX: Int, endX: Int) {
        if (mCurrPosition < 0) {
            mCurrPosition = 0
        }
        if (mCurrPosition > childCount - 1) {
            mCurrPosition = childCount - 1
        }
        if ((endX - startX) > width / 2 && mCurrPosition > 0) {
            scrollTo(--mCurrPosition * width, 0)
        } else if ((startX - endX) > width / 2 && mCurrPosition < childCount - 1) {
            scrollTo(++mCurrPosition * width, 0)
        } else {
            scrollTo(mCurrPosition * width, 0)
        }
    }
}