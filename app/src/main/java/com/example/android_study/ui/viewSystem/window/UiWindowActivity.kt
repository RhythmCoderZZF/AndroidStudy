package com.example.android_study.ui.viewSystem.window

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_ui_window.*

class UiWindowActivity : BaseActivity() {
    private val mView = mutableListOf<View>()
    private var time = 0
    private var mRevert = false

    override fun getLayoutId() = R.layout.activity_ui_window

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_open.setOnClickListener {
            if (time > 2) {
                Toast.makeText(this, "cant", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val layoutParams = WindowManager.LayoutParams(
                200,
                200, 0, 0, PixelFormat.TRANSPARENT
            )
            val btn = View(this)
            when (time) {
                2 -> {
                    btn.setBackgroundColor(Color.GREEN)
                    layoutParams.x = 100
                    layoutParams.y = 100
                    layoutParams.title = "MyWindow3"
                    layoutParams.type =
                        if (mRevert) WindowManager.LayoutParams.TYPE_APPLICATION else WindowManager.LayoutParams.TYPE_APPLICATION + 4
                }
                1 -> {
                    btn.setBackgroundColor(Color.RED)
                    layoutParams.x = 50
                    layoutParams.y = 50
                    layoutParams.title = "MyWindow2"
                    layoutParams.type =
                        if (mRevert) WindowManager.LayoutParams.TYPE_APPLICATION + 2 else WindowManager.LayoutParams.TYPE_APPLICATION + 2
                }
                0 -> {
                    btn.setBackgroundColor(Color.BLUE)
                    layoutParams.x = 0
                    layoutParams.y = 0
                    layoutParams.title = "MyWindow1"
                    layoutParams.type =
                        if (mRevert) WindowManager.LayoutParams.TYPE_APPLICATION + 4 else WindowManager.LayoutParams.TYPE_APPLICATION
                }
            }
            layoutParams.gravity = Gravity.START or Gravity.TOP
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

            windowManager.addView(btn, layoutParams)
            mView.add(btn)
            time++
        }
        btn_close.setOnClickListener {
            mView.forEach {
                windowManager.removeView(it)
            }
            mView.clear()
            time = 0
        }

        btn_revert_z_order.setOnClickListener {
            mRevert = !mRevert
        }
    }
}