package com.example.android_study.dispatchEvent.interceptEvent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2020/11/6
 * Description:
 */
class MyInterceptViewGroup(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var intercept = false

    init {
        postDelayed({
            intercept = true
            CmdUtil.i("拦截事件$intercept！")
        }, 3000)
        postDelayed({
            intercept = false
            CmdUtil.i("放弃~$intercept")
        }, 8000)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            ACTION_DOWN -> {
                CmdUtil.i("ACTION_DOWN")
            }
            ACTION_MOVE -> {
                CmdUtil.v("ACTION_MOVE")
            }
            ACTION_UP -> {
                CmdUtil.e("ACTION_UP")
            }
        }
        return intercept
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        when (event?.action) {
            ACTION_DOWN -> {
                CmdUtil.i(">> ACTION_DOWN")
            }
            ACTION_MOVE -> {
                CmdUtil.v(">> ACTION_MOVE")
            }
            ACTION_UP -> {
                CmdUtil.e(">> ACTION_UP")
            }
        }
        return true
    }
}