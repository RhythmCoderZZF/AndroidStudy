package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil


class UIGlobalFocusActivity : BaseActivity() {
    private lateinit var listener: ViewTreeObserver.OnGlobalFocusChangeListener
    init {
        listener=ViewTreeObserver.OnGlobalFocusChangeListener{_,_->
            CmdUtil.v("FocusChange")
            window.decorView.viewTreeObserver.removeOnGlobalFocusChangeListener(listener)
        }
    }
    override fun getLayoutId() = R.layout.activity_u_i_view_system_global_focus_observer

    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.viewTreeObserver.addOnGlobalFocusChangeListener (listener)
    }
}