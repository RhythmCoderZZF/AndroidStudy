package com.example.android_study.ui.materialDesign.coordinatorLayout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_coordinator_layout.*

class CoordinatorLayoutActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_coordinator_layout

    @SuppressLint("ClickableViewAccessibility")
    override fun initViewAndData(savedInstanceState: Bundle?) {
        button.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                v.x = event.rawX - v.width / 2;
                v.y = event.rawY - v.height / 2;
            }
            true
        }
    }
}