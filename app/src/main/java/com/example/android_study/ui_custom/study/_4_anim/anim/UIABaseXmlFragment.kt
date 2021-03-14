package com.example.android_study.ui_custom.study._4_anim.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_anim_base.*

class UIABaseXmlFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        CmdUtil.showWindow()
        return inflater.inflate(R.layout.fragment_u_i_anim_base, container, false)
    }

    override fun onResume() {
        super.onResume()

        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.ui_cus_scale)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                CmdUtil.v("width:${iv1.width}|height:${iv1.height}")
            }

            override fun onAnimationEnd(animation: Animation?) {
                CmdUtil.i("width:${iv1.width}|height:${iv1.height}")
            }

            override fun onAnimationRepeat(animation: Animation?) {
                CmdUtil.i("width:${iv1.width}|height:${iv1.height}")
            }
        })
        iv1.startAnimation(anim)
        val anim1 = AnimationUtils.loadAnimation(requireContext(), R.anim.ui_cus_rotate)
        anim1.interpolator=BounceInterpolator()
        iv2.startAnimation(anim1)

        val animSet = AnimationUtils.loadAnimation(requireContext(), R.anim.ui_cus_set)
        iv3.startAnimation(animSet)
    }
}