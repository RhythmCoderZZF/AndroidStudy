package com.example.android_study.performance.layout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_performance_layout.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/28
 * Description:
 */
class PerformanceLayoutActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_performance_layout

    override fun initViewAndData(savedInstanceState: Bundle?) {

        //————————include————————
        Handler(Looper.getMainLooper()).postDelayed({
            val v = findViewById<FrameLayout>(R.id.include)
            v.findViewById<ImageView>(R.id.iv).setImageResource(R.drawable.bg_rain)
        }, 500)

        //————————viewStub————————
        Handler(Looper.getMainLooper()).postDelayed({
            viewStub.visibility = View.VISIBLE
        }, 1000)
    }
}