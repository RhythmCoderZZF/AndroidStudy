package com.example.android_study.event_system.event.conflict

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Author:create by RhythmCoder
 * Date:2021/3/27
 * Description:
 */
class ChildOutConflict @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var lastY=0f
    private var lastX=0f

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = ev.rawX
                lastY = ev.rawY
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