package com.example.android_study.ui.viewSystem.choreographer

import android.os.Bundle
import android.view.Choreographer
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_choreographer.*

class UIChoreographerActivity : BaseActivity() {

    private val runnable = object : Choreographer.FrameCallback {
        override fun doFrame(frameTimeNanos: Long) {
//            CmdUtil.v("$frameTimeNanos")
            CmdUtil.v("1")
            t0.change()
            CmdUtil.v("4")
            Choreographer.getInstance().postFrameCallback(this)
        }
    }

    override fun getLayoutId() = R.layout.activity_u_i_view_system_choreographer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        button.setOnClickListener {
            Choreographer.getInstance().postFrameCallback(runnable)
        }


        button1.setOnClickListener {
            t1.start()
        }
    }

    override fun onPause() {
        super.onPause()
        Choreographer.getInstance().removeFrameCallback(runnable)
    }
}