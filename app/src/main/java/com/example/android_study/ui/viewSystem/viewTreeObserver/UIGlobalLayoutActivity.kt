package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_window_attach_observer.*


class UIGlobalLayoutActivity : BaseActivity() {
    private lateinit var listener: ViewTreeObserver.OnGlobalLayoutListener
    init {
        listener= ViewTreeObserver.OnGlobalLayoutListener{
            CmdUtil.v("中心TextView宽高：${text.width}|${text.height}")
            window.decorView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    override fun getLayoutId() = R.layout.activity_u_i_view_system_window_attach_observer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        text.text="GlobalLayoutListener"
        window.decorView.viewTreeObserver.addOnGlobalLayoutListener (listener)
    }
}