package com.example.android_study.ui_custom.study._demo.paint_shader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2021/2/28
 * Description:
 */
class VShaderLook @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_light_haze)
    private val mPaint = Paint().apply {
        shader = BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR)
    }

    private var mX = 0f
    private var mY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                mX = event.x
                mY = event.y
                invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                mX = event.x
                mY = event.y
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(mX, mY, 150f, mPaint)
    }
}