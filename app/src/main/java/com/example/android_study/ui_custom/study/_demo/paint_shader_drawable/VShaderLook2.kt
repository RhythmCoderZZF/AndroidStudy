package com.example.android_study.ui_custom.study._demo.paint_shader

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2021/2/28
 * Description:
 */
class VShaderLook2 @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val SCALE = 2
    private val RADIUS = 100

    private var mBitmap: Bitmap? = null
    private val shapeDrawable = ShapeDrawable(OvalShape())
    private val mMatrix = Matrix()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                mMatrix.setTranslate(-x * SCALE + RADIUS, -y * SCALE + RADIUS)
                shapeDrawable.paint.shader.setLocalMatrix(mMatrix)

                shapeDrawable.setBounds(x.toInt() - RADIUS, y.toInt() - RADIUS, x.toInt() + RADIUS, y.toInt() + RADIUS)
                invalidate()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                mMatrix.setTranslate(-event.x * SCALE + RADIUS, -event.y * SCALE + RADIUS)
                shapeDrawable.paint.shader.setLocalMatrix(mMatrix)

                shapeDrawable.setBounds(x.toInt() - RADIUS, y.toInt() - RADIUS, x.toInt() + RADIUS, y.toInt() + RADIUS)
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        if (mBitmap == null) {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg_clear_night)
            mBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)
            val bitmapShader = BitmapShader(Bitmap.createScaledBitmap(mBitmap!!, width * SCALE, height * SCALE, false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            shapeDrawable.paint.apply {
                shader = bitmapShader
            }
//            shapeDrawable.bounds = Rect(100, 400, 100 + RADIUS, 400 + RADIUS)
        }
        mBitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, null)
        }
        shapeDrawable.draw(canvas)
    }
}