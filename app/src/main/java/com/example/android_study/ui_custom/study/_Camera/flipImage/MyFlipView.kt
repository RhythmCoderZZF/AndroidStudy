package com.example.android_study.ui_custom.study._Camera.flipImage

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/16
 * Description:
 */
class MyFlipView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mMatrix = Matrix()
    private val mCamera = Camera()
    private var mReserve = false
    private var mDegree = 0f
    private var mAnimatorFraction = 0f
    private val DEPTH_Z = 1000
    private val mAnim = ValueAnimator.ofFloat(0f, 180f).apply {
        duration = 500
        addUpdateListener {
            mAnimatorFraction = it.animatedFraction

            mDegree = if (mReserve) {
                it.animatedValue as Float
            } else {
                180f - it.animatedValue as Float
            }

            flipChildByDegree()
            invalidate()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount == 2) {
            getChildAt(0).visibility = View.VISIBLE
            getChildAt(1).visibility = View.INVISIBLE
        }
    }

    fun flip() {
        mReserve = !mReserve
        mAnim.start()
    }

    private fun flipChildByDegree() {
        if (childCount == 2) {
            if (mDegree < 90) {
                getChildAt(0).visibility = View.VISIBLE
                getChildAt(1).visibility = View.INVISIBLE
            } else {
                getChildAt(0).visibility = View.INVISIBLE
                getChildAt(1).visibility = View.VISIBLE
            }
        }
    }


    override fun dispatchDraw(canvas: Canvas) {
        mCamera.save()

        if (mAnimatorFraction < 0.5f) {
            mCamera.translate(0f, 0f, DEPTH_Z * mAnimatorFraction)
        } else {
            mCamera.translate(0f, 0f, (DEPTH_Z * (1f - mAnimatorFraction)))
        }
        mCamera.rotateY(mDegree)
        mCamera.getMatrix(mMatrix)
        CmdUtil.v("${mMatrix.toShortString()}")
        mMatrix.apply {
            postTranslate(width / 2f, height / 2f)
            preTranslate(-width / 2f, -height / 2f)
        }
        canvas.concat(mMatrix)
        mCamera.restore()
        super.dispatchDraw(canvas)
    }


}