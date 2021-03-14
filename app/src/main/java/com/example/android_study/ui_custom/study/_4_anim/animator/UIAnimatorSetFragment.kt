package com.example.android_study.ui_custom.study._4_anim.animator

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator_set.*

class UIAnimatorSetFragment : BaseFragment() {
    private lateinit var anim: ValueAnimator
    private lateinit var anim1: ValueAnimator
    private lateinit var anim2: ValueAnimator
    private lateinit var anim3: ValueAnimator
    private lateinit var animSet: AnimatorSet
    private lateinit var animSet1: AnimatorSet

    private lateinit var animO: ObjectAnimator
    private lateinit var animO1: ObjectAnimator
    private lateinit var animO2: ObjectAnimator
    private lateinit var animO3: ObjectAnimator
    private lateinit var animSetO: AnimatorSet
    private lateinit var animSetO1: AnimatorSet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* AnimatorSet */
        anim = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
            addUpdateListener {
                val value = it.animatedValue as Float
                iv.translationX = value
            }
        }
        anim1 = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
            addUpdateListener {
                val value = it.animatedValue as Float
                iv.rotationY = value
            }
        }

        anim2 = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
            addUpdateListener {
                val value = it.animatedValue as Float
                iv1.translationX = value
            }
        }
        anim3 = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 2000
            interpolator = BounceInterpolator()
            addUpdateListener {
                val value = it.animatedValue as Float
                iv1.rotation = value
            }
        }
        animSet = AnimatorSet()
        animSet1 = AnimatorSet()
        animSet.playTogether(anim, anim1)
        animSet1.playSequentially(anim2, anim3)

        animSet.addListener(object : Animator.AnimatorListener {
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

        /* AnimatorSet Builder */
        animO = ObjectAnimator.ofFloat(iv2, "translationX", 0f, 720f).apply {
            duration = 4000
            interpolator = OvershootInterpolator()
        }
        animO1 = ObjectAnimator.ofFloat(iv2, "rotationX", 0f, 720f).apply {
            duration = 4000
            interpolator = OvershootInterpolator()
        }
        animO2 = ObjectAnimator.ofFloat(iv3, "translationX", 0f, 720f).apply {
            duration = 4000
            interpolator = OvershootInterpolator()
        }
        animO3 = ObjectAnimator.ofFloat(iv3, "rotationY", 0f, 720f).apply {
            duration = 4000
            interpolator = OvershootInterpolator()
        }
        AnimatorSet().apply {
            animSetO = this
        }.play(animO).with(animO1)

        AnimatorSet().apply {
            animSetO1 = this
        }.play(animO2).before(animO3)

    }

    override fun onResume() {
        super.onResume()
        animSet.start()
        animSet1.start()
        animSetO.start()
        animSetO1.start()
    }

    override fun onPause() {
        super.onPause()
        animSet.cancel()
        animSet1.cancel()
    }
}