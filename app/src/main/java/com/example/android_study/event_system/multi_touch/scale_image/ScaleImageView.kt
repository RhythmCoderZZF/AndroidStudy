package com.example.android_study.event_system.multi_touch.scale_image

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.ImageView
import com.example.android_study._base.cmd.CmdUtil
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Author:create by RhythmCoder
 * Date:2021/8/30
 * Description:
 */
class ScaleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    private val mMatrix = Matrix()
    private var mOldDist = 0f
    private var mNewDist = 0f
    private var mFingers = 0
    private val slop = ViewConfiguration.getTouchSlop()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                reset()
                mFingers+=1
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mFingers += 1
                if (mFingers == 2) {
                    mOldDist = spacing(event)
                }
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mFingers -= 1
                if (mFingers == 2) {
                    mOldDist = spacing(event)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (mFingers >= 2) {
                    mNewDist = spacing(event)
                    if (abs(mNewDist - mOldDist) > 1) {
                        zoom()
                        mOldDist = mNewDist
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                reset()
            }
        }
        return true
    }

    private fun reset() {
        mOldDist = 0f
        mFingers = 0
        mNewDist = 0f
    }

    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt(x * x + y * y)
    }

    private fun zoom() {
        val scale = mNewDist / mOldDist
        CmdUtil.v("scale:$scale")
        mMatrix.postScale(scale, scale)
        imageMatrix = mMatrix
    }
}