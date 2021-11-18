package com.example.android_study.ui_custom.demo.flipbord.flipViewGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_cus_flip_view_group.*

/**
 * Author:create by RhythmCoder
 * Date:2021/9/7
 * Description:
 */
class FragmentFlipViewGroup : BaseFragment() {

    private val leftL= listOf(R.drawable.bg_clear_day,R.drawable.bg_wind,R.drawable.bg_snow)
    private val rightL= listOf(R.drawable.bg_cloudy,R.drawable.bg_fog,R.drawable.bg_clear_night)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_u_i_cus_flip_view_group, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flipVG.setOnTouchListener { v, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                }
                MotionEvent.ACTION_UP -> {
                    v.performClick()
                }
            }
            false
        }

        flipVG.setViewList(listOf(0,1,2).map {
            val vc = LayoutInflater.from(context)
                .inflate(R.layout.layout_flip_view_content, flipVG, false) as FlipContentView
            if (it == 0) {
                vc.setNotInitRotate()
            }
            val vl=vc.findViewById<View>(R.id.left)
            val vr=vc.findViewById<View>(R.id.right)
            vl.setBackgroundResource(leftL[it])
            vr.setBackgroundResource(rightL[it])
            vc
        })
    }
}