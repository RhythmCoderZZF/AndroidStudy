package com.example.android_study.ui_custom.study._Packge_View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/6
 * Description:
 */
class _MyPackgeView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mPaint = Paint()
    private var mShape = -1
    private var mSize = 0f

    init {
        context.obtainStyledAttributes(attrs, R.styleable._MyPackageView).apply {
            try {
                val mColor = getColor(R.styleable._MyPackageView_fillColor, Color.BLACK)
                val size1 = getDimensionPixelSize(R.styleable._MyPackageView_r, 200)
                val mSize2 = getDimensionPixelOffset(R.styleable._MyPackageView_r, 200)
                mSize = getDimension(R.styleable._MyPackageView_r, 200f)
                CmdUtil.v("Dimension:$mSize DimensionPixelSize:$size1 DimensionPixelOffset:$mSize2")
                mShape = getInt(R.styleable._MyPackageView_shape, -1)
                mPaint.apply {
                    color = mColor
                    style = Paint.Style.FILL
                    isAntiAlias = true
                }
            } finally {
                recycle()
            }
        }

    }

    override fun onDraw(canvas: Canvas) {
        if (mShape == 0) {
            canvas.withTranslation(width / 2f, height / 2f) {
                canvas.drawRect(-mSize, -mSize, mSize, mSize, mPaint)
            }
        } else {
            canvas.drawCircle(width / 2f, height / 2f, mSize, mPaint)
        }
    }
}