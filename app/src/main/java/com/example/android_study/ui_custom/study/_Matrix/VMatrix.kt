package com.example.android_study.ui_custom.study._Matrix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withMatrix
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/8
 * Description:
 */
class VMatrix @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

private val mDrawMatrix=Matrix()


    override fun onDraw(canvas: Canvas) {
        mDrawMatrix.reset()
        canvas.concat(mDrawMatrix.apply {
            postRotate(30f)
            postRotate(15f)
            postScale(2f, 2f)
            postTranslate(100f, 0f)

            val floatArray= FloatArray(9)
            mDrawMatrix.getValues(floatArray)
            CmdUtil.v(floatArray.toList().toString())
        })
        canvas.drawText("hello Matrix", 0f, 40f, Paint().apply {
            textSize = 60f
            style = Paint.Style.STROKE
            strokeWidth=4f
        })
    }
}