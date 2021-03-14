package com.example.android_study.ui_custom.study._Drawable._drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.withTranslation

/**
 * Author:create by RhythmCoder
 * Date:2021/3/2
 * Description:
 */
class VRoundDrawable(val mBitmap: Bitmap) : Drawable() {

    private val mPaint = Paint()
    private var mBitmapShader: BitmapShader? = null
    override fun draw(canvas: Canvas) {
        canvas.drawCircle(bounds.width() / 2f, bounds.height() / 2f, if (bounds.width() > bounds.height()) bounds.width() / 2f else bounds.height() / 2f, mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }
    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT//有透明度

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mBitmapShader = BitmapShader(Bitmap.createScaledBitmap(mBitmap, right - left, bottom - top, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mPaint.shader = mBitmapShader
    }

    override fun getIntrinsicHeight(): Int {
        return mBitmap.height
    }

    override fun getIntrinsicWidth() = mBitmap.width

    override fun getMinimumHeight(): Int {
        return super.getMinimumHeight()
    }
}