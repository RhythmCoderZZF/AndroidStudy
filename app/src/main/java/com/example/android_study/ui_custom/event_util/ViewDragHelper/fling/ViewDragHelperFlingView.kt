package com.example.android_study.ui_custom.event_util.ViewDragHelper.fling

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ViewDragHelperFlingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var mDragHelper: ViewDragHelper

    init {
        CmdUtil.showWindow()
        mDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                CmdUtil.v("tryCaptureView :${child.id}|$pointerId")
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                CmdUtil.v("clamp H:$left|$dx")
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                CmdUtil.v("clamp V:$top|$dy")
                return top
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
                mDragHelper.flingCapturedView(
                    0,
                    0,
                    width - releasedChild.width,
                    height - releasedChild.height
                )
                invalidate()
            }

        })
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
}