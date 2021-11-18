package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_window_attach_observer.*


class UIDrawActivity : BaseActivity() {
    private var drawListener: ViewTreeObserver.OnDrawListener? = null

    init {
        drawListener = ViewTreeObserver.OnDrawListener {
            CmdUtil.v("绘制")
        }
    }
    override fun getLayoutId() = R.layout.activity_u_i_view_system_window_attach_observer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        text.text="OnDrawListener"
        window.decorView.viewTreeObserver.addOnDrawListener(drawListener)
    }
}