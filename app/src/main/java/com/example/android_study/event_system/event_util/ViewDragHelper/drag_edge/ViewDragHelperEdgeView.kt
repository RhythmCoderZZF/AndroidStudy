package com.example.android_study.event_system.event_util.ViewDragHelper.drag_edge

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
class ViewDragHelperEdgeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private lateinit var mDragHelper: ViewDragHelper

    init {
        CmdUtil.showWindow()
        mDragHelper = ViewDragHelper.create(this, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
                return left
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                return top
            }

            override fun onEdgeTouched(edgeFlags: Int, pointerId: Int) {
                CmdUtil.v("onEdgeTouched:$edgeFlags")
            }

            override fun onEdgeLock(edgeFlags: Int): Boolean {
                CmdUtil.v("onEdgeLock:$edgeFlags")
                return edgeFlags == ViewDragHelper.EDGE_LEFT
            }

            override fun onEdgeDragStarted(edgeFlags: Int, pointerId: Int) {
                CmdUtil.v("onEdgeDragStarted:$edgeFlags")
                mDragHelper.captureChildView(this@ViewDragHelperEdgeView.getChildAt(0), pointerId)
            }
        })
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        return true
    }
}