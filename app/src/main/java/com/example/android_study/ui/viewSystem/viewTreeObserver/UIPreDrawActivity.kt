package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_window_attach_observer.*


class UIPreDrawActivity : BaseActivity() {
    private var preDrawListener: ViewTreeObserver.OnPreDrawListener? = null

    init {
        preDrawListener = ViewTreeObserver.OnPreDrawListener {
            CmdUtil.v("跳过绘制")
            window.decorView.viewTreeObserver.removeOnPreDrawListener(preDrawListener)
            false
        }
    }

    override fun getLayoutId() = R.layout.activity_u_i_view_system_window_attach_observer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        text.text = "OnPreDrawListener"
        window.decorView.viewTreeObserver.addOnPreDrawListener(preDrawListener)
    }
}