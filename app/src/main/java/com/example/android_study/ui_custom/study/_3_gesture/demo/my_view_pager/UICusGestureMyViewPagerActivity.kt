package com.example.android_study.ui_custom.study._3_gesture.demo.my_view_pager

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_u_i_cus_gesture_demo_my_view_pager.*
import kotlinx.android.synthetic.main.item_image.*

class UICusGestureMyViewPagerActivity : BaseActivity() {

    private val pics = arrayOf(R.drawable.bg_clear_day, R.drawable.bg_clear_night, R.drawable.bg_cloudy, R.drawable.bg_partly_cloudy_day)
    override fun getLayoutId() = R.layout.activity_u_i_cus_gesture_demo_my_view_pager

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        var index = 0
        pics.iterator().forEach { pic ->
            if (index == 0) {
                val moveImage = MyViewPager.MovePic(this).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    scaleType = ImageView.ScaleType.CENTER
                    setBackgroundColor(Color.GRAY)
                    setImageResource(R.mipmap.ic_launcher_round)
                }
                myViewPager.addView(moveImage)
            }
            index++

            val imageView = ImageView(this).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                scaleType = ImageView.ScaleType.CENTER
                setImageResource(pic)

            }
            myViewPager.addView(imageView)
        }

    }




}