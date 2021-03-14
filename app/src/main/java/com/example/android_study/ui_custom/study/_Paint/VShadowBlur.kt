package com.example.android_study.ui_custom.study._Paint

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withTranslation
import com.example.android_study.R
import com.example.android_study._base.util.dp2px


/**
 * Author:create by RhythmCoder
 * Date:2021/2/1
 * Description:
 */
class VShadowBlur @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
    }

    private var mTextSize = 50f
    private var mTextSizeMove = mTextSize + 30f
    private var textPaint = TextPaint().apply {
        textSize = mTextSize
    }


    /* ShadowLayer */
    private val mPaintShadowLayer = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        isAntiAlias = true
        setShadowLayer(5f, 20f, 10f, Color.RED)
        textSize = 60f
    }

    /* BlurMaskFilter */
    private val mPaintBlurMaskFilter = Paint().apply {
        style = Paint.Style.FILL
        color = Color.RED
        isAntiAlias = true
        maskFilter = BlurMaskFilter(5f, BlurMaskFilter.Blur.INNER)
        textSize = 55f
    }
    private val bitmapShadowLayer = BitmapFactory.decodeResource(resources, R.drawable.bg_clear_day)


    override fun onDraw(canvas: Canvas) {
        /* 1 */
        canvas.drawText("1. ShadowLayer(阴影)${canvas.isHardwareAccelerated}", 0f, mTextSize, textPaint)
        canvas.translate(0f, mTextSizeMove)
        canvas.drawText("阴影", 100f, 60f, mPaintShadowLayer)
        canvas.drawRect(100f, 100f, 600f, 300f, mPaintShadowLayer)
        canvas.drawBitmap(bitmapShadowLayer, null, RectF(100f, 350f, 400f, 600f), mPaintShadowLayer)

        /* 2 */
        canvas.translate(0f, 700f)
        canvas.drawText("2. BlurMaskFilter(模糊)", 0f, mTextSize, textPaint)
        canvas.translate(0f, mTextSizeMove)
        canvas.drawText("内模糊(INNER)", 100f, 60f, mPaintBlurMaskFilter)
        canvas.withTranslation(500f, 0f) {
            canvas.drawText("内外模糊(NORMAL)", 100f, 60f, mPaintBlurMaskFilter.apply {
                maskFilter = BlurMaskFilter(5f, BlurMaskFilter.Blur.NORMAL)
            })
        }
        canvas.translate(0f, 60f)
        canvas.drawText("仅外模糊(OUTER)", 100f, 60f, mPaintBlurMaskFilter.apply {
            maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.OUTER)
        })
        canvas.withTranslation(500f, 0f) {
            canvas.drawText("外模糊(SOLID)", 100f, 60f, mPaintBlurMaskFilter.apply {
                maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
            })
        }
        canvas.drawRect(100f, 100f, 300f, 300f, mPaintBlurMaskFilter)
        canvas.drawBitmap(bitmapShadowLayer, null, RectF(100f, 350f, 400f, 800f), mPaintBlurMaskFilter.apply {
            maskFilter = BlurMaskFilter(100f, BlurMaskFilter.Blur.NORMAL)
        })
    }
}