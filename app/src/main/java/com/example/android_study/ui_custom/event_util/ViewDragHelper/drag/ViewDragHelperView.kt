package com.example.android_study.ui_custom.event_util.ViewDragHelper.drag

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.customview.widget.ViewDragHelper
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ViewDragHelperView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var mDragHelper: ViewDragHelper
    private var change=false

    init {
        CmdUtil.showWindow()
        mDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                CmdUtil.v("tryCaptureView :${child.id}|$pointerId")
                return when (child.id) {
                    R.id.canscroll,
                    R.id.view -> true
                    else -> false
                }
            }

            override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
                CmdUtil.v("onViewCaptured :${capturedChild.id}|$activePointerId")
                super.onViewCaptured(capturedChild, activePointerId)
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                CmdUtil.v("clamp H:$left|$dx")
                if (child.id == R.id.view) {
                    (child as FlashView).logInvalidate()
                }
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                CmdUtil.v("clamp V:$top|$dy")
                return top
            }

            override fun getViewHorizontalDragRange(child: View): Int {
                return 1
            }

            override fun getViewVerticalDragRange(child: View): Int {
                return 1
            }
        })
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mDragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }
}