package com.example.android_study.ui_custom.study._Camera

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.widget.ImageView
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/14
 * Description:
 */
@SuppressLint("AppCompatCustomView")
class RCameraView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    private val mCamera = Camera()
    private var mDegree = 0f

    /* 自定义属性 */
    private var mRotation = 0
    private var mCameraMoveToCenter = false
    private val mMatrix = Matrix()

    init {
        context.obtainStyledAttributes(attrs, R.styleable.RCameraView).apply {
            try {
                mRotation = getInt(R.styleable.RCameraView_rotation, 0)
                mCameraMoveToCenter = getBoolean(R.styleable.RCameraView_cameraMoveToCenter, false)
            } finally {
                recycle()
            }
        }

    }

    private val mAnim = ValueAnimator.ofFloat(0f, 360f).apply {
        duration = 5000
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener {
            val value = it.animatedValue as Float
            mDegree = value
            invalidate()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        CmdUtil.v("onAttachedToWindow")
        mAnim.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        CmdUtil.v("onAttachedToWindow")
        mAnim.cancel()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        mCamera.save()

        when (mRotation) {
            0 -> mCamera.rotateX(mDegree)
            1 -> mCamera.rotateY(mDegree)
            2 -> mCamera.rotateZ(mDegree)
        }
        mCamera.getMatrix(mMatrix)
        if (mCameraMoveToCenter) {
            mMatrix.postTranslate(width / 2f, height / 2f)
            mMatrix.preTranslate(-width / 2f, -height / 2f)
        }
        canvas.setMatrix(mMatrix)
        mCamera.restore()
        super.onDraw(canvas)
        canvas.restore()
    }
}