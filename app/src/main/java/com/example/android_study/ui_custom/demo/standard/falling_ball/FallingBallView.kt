package com.example.android_study.ui_custom.demo.standard.falling_ball

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.marginLeft
import androidx.core.view.marginTop

/**
 * Author:create by RhythmCoder
 * Date:2021/2/18
 * Description:
 */
class FallingBallView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {


    fun setFall(value: Point) {
        layout(value.x, value.y, value.x + width, value.y + height)
    }

    fun getFall() = Point(marginLeft, marginTop)
}