package com.example.android_study.ui_custom.demo.flipbord.flipViewGroup

import android.graphics.Camera

/**
 * Author:create by RhythmCoder
 * Date:2021/9/7
 * Description:
 */
interface FlipListener {
    fun onFlip(rotateL:Float,rotateR:Float)

    fun onFlipEnd(isTop:Boolean)
}