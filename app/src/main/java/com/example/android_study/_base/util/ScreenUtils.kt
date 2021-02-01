package com.example.android_study._base.util

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.example.android_study.R

/**
 * @author kuky.
 * @description
 */
val screenWidth = Resources.getSystem().displayMetrics.widthPixels

val screenHeight = Resources.getSystem().displayMetrics.heightPixels

val screenDensity = Resources.getSystem().displayMetrics.density

fun Float.dp2px() = (screenDensity * this + 0.5f).toInt()


fun Activity.immersion() {
    window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.statusBarColor = Color.TRANSPARENT
}

fun Activity.darkStatusBarText() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun Activity.lightStatusBarText() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}

fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = ContextCompat.getColor(this, color)
}
