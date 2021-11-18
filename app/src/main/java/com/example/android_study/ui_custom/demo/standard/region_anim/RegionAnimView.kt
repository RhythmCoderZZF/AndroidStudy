package com.example.android_study.ui_custom.demo.standard.region_anim

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2021/2/8
 * Description:
 */
class RegionAnimView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object {
        private const val CLIP_HEIGHT = 30f
    }
    private var start=false


    private var mBitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_clear_night)
    private val mPath = Path()
    private val mRect = RectF()
    private var clipWidth = 0f

    fun start() {
        start=true
        clipWidth=0f
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        if (!start) {
            return
        }
        mPath.reset()

        var i = 0

        while (i * CLIP_HEIGHT <= height) {
            if (i % 2 == 0) {
                mPath.addRect(mRect.apply {
                    left = 0f
                    top = i * CLIP_HEIGHT
                    right = clipWidth
                    bottom = (i + 1) * CLIP_HEIGHT
                },Path.Direction.CCW)
            } else {
                mPath.addRect(mRect.apply {
                    left = width - clipWidth
                    top = i * CLIP_HEIGHT
                    right = width.toFloat()
                    bottom = (i + 1) * CLIP_HEIGHT
                },Path.Direction.CCW)
            }
            i++
        }
        canvas.clipPath(mPath)
        canvas.drawBitmap(mBitmap,null,Rect(0,0,width,height),Paint())
        if (clipWidth >= width) {
            return
        }
        clipWidth+=5
        invalidate()
    }

}