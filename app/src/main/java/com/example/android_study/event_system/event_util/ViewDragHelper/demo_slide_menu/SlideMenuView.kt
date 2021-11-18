package com.example.android_study.event_system.event_util.ViewDragHelper.demo_slide_menu

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.customview.widget.ViewDragHelper
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class SlideMenuView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mMainView: View? = null
    private var mMenuView: View? = null
    private val mMaxSlideWidth = 500
    private lateinit var mDragHelper: ViewDragHelper

    private var once = true

    init {
        CmdUtil.showWindow()
        mDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return child == mMainView
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return when {
                    left in 1 until mMaxSlideWidth -> {
                        left
                    }
                    left < 0 -> {
                        0
                    }
                    else -> {
                        mMaxSlideWidth
                    }
                }
            }

            override fun onViewPositionChanged(
                changedView: View,
                left: Int,
                top: Int,
                dx: Int,
                dy: Int
            ) {
                transitionMainView(left.toFloat())
                transitionMenuView(left.toFloat())
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                if (releasedChild.left > mMaxSlideWidth / 2) {
                    mDragHelper.settleCapturedViewAt(mMaxSlideWidth, 0)
                } else {
                    mDragHelper.settleCapturedViewAt(0, 0)
                }
                invalidate()
            }
        })

        this.viewTreeObserver.addOnDrawListener {
            if (once) {
                once = false
                mMenuView?.let {
                    it.offsetLeftAndRight(-it.width)
                }
            }
        }
    }

    /**
     * API————————————————————————————————————————————————————————————————————————————
     */
    /**
     * 设置主体View
     */
    fun setMainView(view: View) {
        mMainView = view
        addView(view)
    }

    /**
     * 设置Menu View
     */
    fun setMenuView(view: View) {
        mMenuView = view
        addView(view, 0)
    }

    /**
     * 关闭Menu
     */
    fun closeMenu() {
        mMainView?.let {
            mDragHelper.smoothSlideViewTo(it, 0, 0)
            invalidate()
        }
    }

    /**
     * 打开Menu
     */
    fun openMenu() {
        mMainView?.let {
            mDragHelper.smoothSlideViewTo(it, mMaxSlideWidth, 0)
            invalidate()
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate()
        }
    }

    private fun transitionMenuView(dx: Float) {
        mMenuView?.translationX = dx
    }

    private fun transitionMainView(dx: Float) {
        val fraction = 1 - 0.1f * dx / mMaxSlideWidth
        mMainView?.scaleX = fraction
        mMainView?.scaleY = fraction
        CmdUtil.v("${mMainView?.width}|${mMainView?.height}")
    }

}