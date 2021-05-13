package com.example.android_study.ui_custom.event.conflict

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.dp2px
import kotlin.math.abs

/**
 * Author:create by RhythmCoder
 * Date:2021/3/27
 * Description:
 */
class FatherOutConflict @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var startX = 0f
    private var startY = 0f

    private var lastX = 0f
    private var lastY = 0f

    private val slop = 10f.dp2px()
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var intercept = false
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                var offsetX = abs(ev.x - startX)
                var offsetY = abs(ev.y - startY)
                if (offsetX > slop || offsetY > slop) {
                    if (offsetY > offsetX) {
                        CmdUtil.e("外部拦截")
                        intercept = true
                    }
                }
            }
        }
        lastX = ev.rawX
        lastY = ev.rawY
        return intercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                translationX = ev.rawX - lastX
                translationY = ev.rawY - lastY
            }
        }
        lastX = ev.rawX
        lastY = ev.rawY
        return super.onTouchEvent(ev)
    }
}