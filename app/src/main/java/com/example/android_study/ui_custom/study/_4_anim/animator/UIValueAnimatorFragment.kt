package com.example.android_study.ui_custom.study._4_anim.animator

import android.animation.Animator
import android.animation.ValueAnimator
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
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator.*

class UIValueAnimatorFragment : BaseFragment() {
    private lateinit var anim: ValueAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CmdUtil.showWindow()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anim = ValueAnimator.ofFloat(0f, -360f, 360f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
        anim.addUpdateListener {
            val value = it.animatedValue as Float
            iv.translationX = value
        }
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                CmdUtil.v("onAnimationStart")
            }

            override fun onAnimationEnd(animation: Animator?) {
                CmdUtil.v("onAnimationEnd")
            }

            override fun onAnimationCancel(animation: Animator?) {
                CmdUtil.v("onAnimationCancel")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                CmdUtil.v("onAnimationRepeat")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        anim.start()
    }

    override fun onPause() {
        super.onPause()
        anim.cancel()
    }
}