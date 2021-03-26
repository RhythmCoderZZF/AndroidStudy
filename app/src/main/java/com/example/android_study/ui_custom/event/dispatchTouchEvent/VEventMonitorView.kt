package com.example.android_study.ui_custom.event.dispatchTouchEvent

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/3/25
 * Description:
 */
@SuppressLint("Recycle")
class VEventMonitorView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var interceptDown = false
    var interceptMove = false
    var consume = false
    var consumeOther = false
    private var mTag = this.javaClass.simpleName

    init {
        context.obtainStyledAttributes(attrs, R.styleable.EventMonitorView).apply {
            try {
                mTag = getString(R.styleable.EventMonitorView_tag) ?: this.javaClass.simpleName
            } finally {
                recycle()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        var event = ""
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                event = "DOWN"
            }
            MotionEvent.ACTION_MOVE -> {
                event = "MOVE"
            }
            MotionEvent.ACTION_UP -> {
                event = "UP"
            }
        }
        CmdUtil.v("$mTag:dispatch()>$event")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var event = ""
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                event = "DOWN"
                CmdUtil.v("$mTag:onIntercept()>$event")
                if (interceptDown) {
                    CmdUtil.e("$mTag:拦截DOWN")
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                event = "MOVE"
                CmdUtil.v("$mTag:onIntercept()>$event")
                if (interceptMove) {
                    CmdUtil.e("$mTag:拦截MOVE")
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                event = "UP"
                CmdUtil.v("$mTag:onIntercept()>$event")
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        var event = ""
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                event = "DOWN"
                CmdUtil.v("$mTag:onTouchEvent()>$event")
                if (consume) {
                    CmdUtil.e("$mTag：消费Down")
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                event = "MOVE"
                CmdUtil.v("$mTag:onTouchEvent()>$event")
                return consumeOther
            }
            MotionEvent.ACTION_UP -> {
                event = "UP"
                CmdUtil.v("$mTag:onTouchEvent()>$event")
                return consumeOther
            }
            MotionEvent.ACTION_CANCEL -> {
                event = "CANCEL"
                CmdUtil.e("$mTag:onTouchEvent()>$event")
            }
        }
        return super.onTouchEvent(ev)
    }
}