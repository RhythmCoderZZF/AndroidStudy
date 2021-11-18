package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import android.view.ViewTreeObserver
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.android.synthetic.main.activity_u_i_view_system_window_attach_observer.*


class UIWindowFocusActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_view_system_window_attach_observer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        text.text="OnWindowFocusChangeListener"
        window.decorView.viewTreeObserver.addOnWindowFocusChangeListener {
            CmdUtil.v("OnWindowFocusChange：$it")
        }
    }
}