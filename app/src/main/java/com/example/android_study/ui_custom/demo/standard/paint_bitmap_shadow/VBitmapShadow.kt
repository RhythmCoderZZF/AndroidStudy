package com.example.android_study.ui_custom.demo.standard.paint_bitmap_shadow

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2021/2/28
 * Description:
 */
class VBitmapShadow @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var originBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_clear_day)
    private var grayBitmap: Bitmap = originBitmap.extractAlpha()

    private val paintGray = Paint().apply {
        color = Color.BLACK
        maskFilter = BlurMaskFilter(40f, BlurMaskFilter.Blur.OUTER)
        textSize = 60f
    }


    override fun onDraw(canvas: Canvas) {

        canvas.drawBitmap(grayBitmap, null, Rect(100, 100, 500, 600), paintGray)
        canvas.drawBitmap(originBitmap, null, Rect(100, 100, 500, 600), null)

    }

}