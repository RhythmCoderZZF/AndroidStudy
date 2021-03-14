package com.example.android_study.ui_custom.study._Drawable._shape_drawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View

/**
 * Author:create by RhythmCoder
 * Date:2021/3/2
 * Description:
 */
class VShapeDrawable @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mDrawable=ShapeDrawable(ArcShape(0f,300f)).apply {
        bounds = Rect(50, 50, 250, 150)
        paint.color= Color.YELLOW
    }

    override fun onDraw(canvas: Canvas) {
        mDrawable.draw(canvas)
    }
}