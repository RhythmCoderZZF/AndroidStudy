package com.example.android_study.ui_custom.demo.standard.loading_image

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.addListener
import com.example.android_study.R
import com.example.android_study._base.util.dp2px

/**
 * Author:create by RhythmCoder
 * Date:2021/2/9
 * Description:
 */
class LoadingImgView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {

    private var mTop = 0
    private var imgIndex = 0

    private val anim = ValueAnimator.ofInt(0, 15f.dp2px(),0).apply {
        interpolator = AccelerateDecelerateInterpolator()
        duration = 2000
//        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        addUpdateListener {
            val i = it.animatedValue as Int
            top = mTop - i
        }
        addListener(onStart = {
            setImageResource(R.drawable.ic_light_rain)
        }, onRepeat = {
            imgIndex++
            when (imgIndex) {
                0 -> setImageResource(R.drawable.ic_light_rain)
                1 -> setImageResource(R.drawable.ic_moderate_rain)
                2 -> setImageResource(R.drawable.ic_heavy_rain)
                3 -> {
                    imgIndex = -1
                    setImageResource(R.drawable.ic_storm_rain)
                }
            }
        })
    }

    fun start() {
        anim.start()
    }

    fun cancel() {
        anim.cancel()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            mTop = top
        }
    }
}