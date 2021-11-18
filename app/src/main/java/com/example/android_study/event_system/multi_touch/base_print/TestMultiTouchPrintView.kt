package com.example.android_study.event_system.multi_touch.base_print

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/8/30
 * Description:
 */
class TestMultiTouchPrintView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    override fun onTouchEvent(event: MotionEvent): Boolean {
        CmdUtil.i("index:${event.actionIndex}")
        CmdUtil.i("id:${event.getPointerId(event.actionIndex)}")
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                CmdUtil.v("Down")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                CmdUtil.v("Pointer Down")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                CmdUtil.v("Pointer UP")
            }
            MotionEvent.ACTION_MOVE -> {
                CmdUtil.v("Move")
            }
            MotionEvent.ACTION_UP -> {
                CmdUtil.v("Up")
            }
        }
        return true
    }
}