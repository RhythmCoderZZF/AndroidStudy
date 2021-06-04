package com.example.android_study.ui.viewSystem.choreographer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import com.example.android_study._base.cmd.CmdUtil
import java.lang.RuntimeException

/**
 * Author:create by RhythmCoder
 * Date:2021/5/19
 * Description:
 */
class MyInvalidateView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var lastTime = 0L
    private var isChange = false
    private var start = false


    fun start() {
        lastTime=System.currentTimeMillis()
        CmdUtil.i("start:$lastTime")
        start = true
        postInvalidateOnAnimation()
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        isChange = !isChange
        val delta = System.currentTimeMillis() - lastTime
        CmdUtil.e("onDraw:$delta")
        lastTime = System.currentTimeMillis()

        if (isChange) {
            canvas?.drawColor(Color.RED)
        } else {
            canvas?.drawColor(Color.BLACK)
        }
        if (start)
            invalidate()
    }
}