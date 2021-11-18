package com.example.android_study.event_system.event_util.ViewDragHelper.scroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/5/15
 * Description:
 */
class ViewDragHelperReleaseView @JvmOverloads constructor(
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
                if (releasedChild.id == R.id.scroll1) {
                    mDragHelper.smoothSlideViewTo(
                        releasedChild,
                        width - releasedChild.width,
                        height - releasedChild.height
                    )
                } else {
                    mDragHelper.settleCapturedViewAt(
                        width - releasedChild.width * 2,
                        height - releasedChild.height
                    )
                }
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
        } else {
            children.forEach {
                if (it.left != 0) {
                    if (it.id == R.id.scroll1) {
                        mDragHelper.smoothSlideViewTo(
                            it,
                            0,
                            0
                        )
                    } else {
                        mDragHelper.smoothSlideViewTo(
                            it,
                            0,
                            it.height+10
                        )
                    }
                    invalidate()
                    ViewCompat.postInvalidateOnAnimation(this)
                }
            }
        }
    }
}