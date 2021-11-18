package com.example.android_study.event_system.event_util.ViewDragHelper.drag

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/5/19
 * Description:
 */
class FlashView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var lastTime = 0L


    fun logInvalidate() {
        lastTime = System.currentTimeMillis()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        val d = System.currentTimeMillis() - lastTime
        CmdUtil.i("执行onDraw:$d")
    }
}