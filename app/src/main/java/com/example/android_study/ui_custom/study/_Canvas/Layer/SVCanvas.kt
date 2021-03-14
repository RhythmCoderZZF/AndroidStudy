package com.example.android_study.ui_custom.study._Canvas.Layer

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withClip

/**
 * Author:create by RhythmCoder
 * Date:2021/3/1
 * Description:
 */
class SVCanvas @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val W = 200
    private val mSrcBitmap = createSrc(W, W)
    private val mDstBitmap = createDst(W / 2)
    private val mPaint = Paint()

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private fun createSrc(w: Int, h: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        Canvas(bitmap).apply {
            drawRect(0f, 0f, w.toFloat(), h.toFloat(), Paint().apply {
                color = Color.BLUE
            })
        }
        return bitmap
    }

    private fun createDst(radius: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888)
        Canvas(bitmap).apply {
            drawCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), Paint().apply {
                color = Color.YELLOW
            })
        }
        return bitmap
    }

    override fun onDraw(canvas: Canvas) {

        canvas.withClip(0f, 0f, width.toFloat(), height / 2.toFloat()) {
            canvas.drawColor(Color.GRAY)
            canvas.drawBitmap(mDstBitmap, 0f, 0f, mPaint)
            mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

            canvas.translate(W.toFloat() / 2, W.toFloat() / 2)
            canvas.drawBitmap(mSrcBitmap, 0f, 0f, mPaint)
            mPaint.xfermode = null

        }
        canvas.withClip(0f, height / 2.toFloat(), width.toFloat(), height.toFloat()) {
            canvas.translate(0f, height / 2f)

            canvas.drawColor(Color.GRAY)
            canvas.saveLayerAlpha(0f, 0f, width.toFloat(), height / 2.toFloat(), 255)
            canvas.drawBitmap(mDstBitmap, 0f, 0f, mPaint)

            mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

            canvas.translate(W.toFloat() / 2, W.toFloat() / 2)
            canvas.drawBitmap(mSrcBitmap, 0f, 0f, mPaint)
            mPaint.xfermode = null
            canvas.restore()
        }

    }
}