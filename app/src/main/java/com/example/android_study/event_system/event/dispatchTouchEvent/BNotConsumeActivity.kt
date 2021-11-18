package com.example.android_study.event_system.event.dispatchTouchEvent

import android.os.Bundle
import android.view.MotionEvent
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class BNotConsumeActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.fragment_u_i_cus_event_dispatch_b_not_consume

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        var event = ""
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                event = "DOWN"
                CmdUtil.i("Activity:onTouchEvent()>$event")
            }
            MotionEvent.ACTION_MOVE -> {
                event = "MOVE"
                CmdUtil.i("Activity:onTouchEvent()>$event")
            }
            MotionEvent.ACTION_UP -> {
                event = "UP"
                CmdUtil.i("Activity:onTouchEvent()>$event")
            }
            MotionEvent.ACTION_CANCEL->{
                event = "CANCEL"
                CmdUtil.i("Activity:onTouchEvent()>$event")
            }
        }
        return super.onTouchEvent(ev)
    }
}