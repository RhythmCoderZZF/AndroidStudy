package com.example.android_study.ui.viewSystem.view_invalidate_requestLayout

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_view_system_view_invalidate_requestlayout.*
import kotlinx.android.synthetic.main.activity_u_i_view_system_view_post.*
import kotlin.concurrent.thread

class _UIViewSystemViewIRActivity : BaseActivity() {

    private var flag = false
    override fun getLayoutId() = R.layout.activity_u_i_view_system_view_invalidate_requestlayout

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btnRequestLayout.setOnClickListener {
            flag = !flag
            (view.layoutParams as LinearLayout.LayoutParams).leftMargin = if(flag) 200 else 0
            view.requestLayout()
        }
    }


}