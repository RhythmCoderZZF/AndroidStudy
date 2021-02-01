package com.example.android_study.ui.viewSystem.choreographer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Choreographer
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.LogUtil
import kotlinx.android.synthetic.main.activity_u_i_choreographer.*

class UIChoreographerActivity : BaseActivity() {
    var mStartTime: Long = 0
    override fun getLayoutId() = R.layout.activity_u_i_choreographer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        button.setOnClickListener {
            Choreographer.getInstance().postFrameCallback(object : Choreographer.FrameCallback {
                override fun doFrame(frameTimeNanos: Long) {
                    mStartTime = frameTimeNanos - mStartTime
                    if (mStartTime / 1000 / 1000 > 16) {
                        t1.text = (mStartTime / 1000 / 1000).toString()
                    }else{
                        t2.text = (mStartTime / 1000 / 1000).toString()
                    }
                    CmdUtil.v("$frameTimeNanos")
                    mStartTime = frameTimeNanos
                    Choreographer.getInstance().postFrameCallback(this)
                }
            })
        }
    }
}