package com.example.android_study.ui.viewSystem.windowManager

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_u_i_view_system_windowmanager.*
import kotlin.concurrent.thread

class UIWindowManagerActivity : BaseActivity() {
    private lateinit var wm: WindowManager
    private lateinit var textView: TextView


    override fun getLayoutId() = R.layout.activity_u_i_view_system_windowmanager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initViewAndData(savedInstanceState: Bundle?) {
        wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        textView = EditText(this).apply {
            hint = "我是WindowManager创建出来的窗口哦"
            textSize = 16f
            setBackgroundColor(Color.argb(0.4f, 225f, 225f, 225f))
        }
        val params = WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.WRAP_CONTENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            type = WindowManager.LayoutParams.LAST_SUB_WINDOW
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            token = this@UIWindowManagerActivity.window.decorView.windowToken
            format = PixelFormat.RGBA_8888
        }
        createWindow.setOnClickListener {
            thread {
                wm.addView(textView, params)
            }

        }
        removeWindow.setOnClickListener {
            wm.removeViewImmediate(textView)
        }
    }

}