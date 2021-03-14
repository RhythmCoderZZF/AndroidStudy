package com.example.android_study.ui_custom.study._Bitmap

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/4
 * Description:
 */
class ShadowImageView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val PADDING = 20f
    private val P = PADDING.toInt()


    init {
        setPadding(P, P, P, P)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        addStateDrawable(Paint().apply {
            color = Color.BLACK
            maskFilter = BlurMaskFilter(PADDING, BlurMaskFilter.Blur.OUTER)
        })
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    private fun addStateDrawable(paint: Paint) {
        val srcBitmap = (drawable as BitmapDrawable).bitmap
        CmdUtil.v("图片getDensity:${srcBitmap.density}")
        val backBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val offset = IntArray(2)
        Canvas(backBitmap).drawBitmap(srcBitmap.extractAlpha(paint, offset),
                offset[0].toFloat() + PADDING, offset[1].toFloat() + PADDING, paint)

        StateListDrawable().apply {
            addState(intArrayOf(android.R.attr.state_pressed), BitmapDrawable(resources, backBitmap))
            background = this
        }
    }


}