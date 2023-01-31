package com.example.android_study.android.ui_system.window

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_android_ui_system_window.*

class UiSystemWindowActivity : BaseActivity() {
    private lateinit var btn: TextView
    private lateinit var layoutParams: WindowManager.LayoutParams

    override fun getLayoutId() = R.layout.activity_android_ui_system_window

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_open.setOnClickListener {
            btn = TextView(this)
            btn.text = "hello"
            btn.textSize = 50f
            btn.setTextColor(Color.RED)
            btn.setBackgroundColor(Color.GREEN)

            layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT
            )
            layoutParams.gravity = Gravity.CENTER or Gravity.TOP
            layoutParams.x = 0
            layoutParams.y = 0
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

            windowManager.addView(btn, layoutParams)
        }
        btn_close.setOnClickListener {
            windowManager.removeView(btn)
        }
    }
}