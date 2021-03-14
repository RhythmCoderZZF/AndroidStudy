package com.example.android_study.ui_custom.study._Paint

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
class VShader @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_clear_night)
    private val mPaint = Paint().apply {
        shader = BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT)
    }

    private val mPaint1 = Paint().apply {
        textSize=60f
        isFakeBoldText=true
        shader = LinearGradient(0f, 0f, 100f, 100f, intArrayOf(Color.RED, Color.GRAY, Color.WHITE, Color.BLUE), null, Shader.TileMode.REPEAT)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(100f, 20f, 500f, 400f, mPaint)

        canvas.drawText("LinearGradient",100f,450f,mPaint1)
    }
}