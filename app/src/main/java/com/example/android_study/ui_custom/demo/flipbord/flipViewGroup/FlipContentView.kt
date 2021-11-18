package com.example.android_study.ui_custom.demo.flipbord.flipViewGroup

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.android_study._base.util.LogUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/9/7
 * Description:
 */
class FlipContentView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), FlipListener {

    private val centerY by lazy {
        height / 2.toFloat()
    }
    private var mRotateR = 0f
    private var mRotateL = 0f

    private var mInitRotate = true

    init {
        setWillNotDraw(false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mInitRotate) {
            getChildAt(0).apply {
                rotationY = 180f
                pivotX = w.toFloat()
                pivotY = h.toFloat() / 2
            }
        }
        getChildAt(0).cameraDistance = 20000f
        getChildAt(1).cameraDistance = 20000f
    }

    override fun onFlip(rotateL: Float, rotateR: Float) {
        LogUtil.d("FLIP", cameraDistance.toString())
        mRotateL = rotateL
        mRotateR = rotateR
        getChildAt(0).apply {
            rotationY = rotateL
            pivotX = width.toFloat()
            pivotY = centerY
        }
        getChildAt(1).apply {
            rotationY = rotateR
            pivotX = 0f
            pivotY = centerY
        }
    }

    override fun onFlipEnd(isTop: Boolean) {
    }


    fun setNotInitRotate() {
        mInitRotate = false
    }
}