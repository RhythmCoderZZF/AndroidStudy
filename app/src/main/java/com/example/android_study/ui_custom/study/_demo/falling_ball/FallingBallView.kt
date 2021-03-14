package com.example.android_study.ui_custom.study._demo.falling_ball

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import com.amap.api.maps.model.Poi
import kotlinx.android.synthetic.main.fragment_u_i_s_d_falling_ball.*

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