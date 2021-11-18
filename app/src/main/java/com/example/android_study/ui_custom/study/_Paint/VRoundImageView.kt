package com.example.android_study.ui_custom.study._Paint

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2021/3/19
 * Description:
 */
@SuppressLint("Recycle")
class VRoundImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var mRadius = 0f
    private val mPath = Path()

    init {
        context.obtainStyledAttributes(attrs, R.styleable.VRoundImageView).apply {
            try {
                mRadius = getDimension(R.styleable.VRoundImageView_radius, 0f)
            } finally {
                recycle()
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mPath.addRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), mRadius, mRadius, Path.Direction.CW)
    }

    override fun draw(canvas: Canvas) {
        canvas.clipPath(mPath)
        super.draw(canvas)
    }


}