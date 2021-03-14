package com.example.android_study.ui_custom.study._4_anim.animator

import android.animation.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator_property_value_holder.*

class UIProperValuesHolderAndKeyframeFragment : BaseFragment() {
    private lateinit var anim: ObjectAnimator
    private lateinit var anim1: ObjectAnimator
    private lateinit var anim2: ValueAnimator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator_property_value_holder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* PropertyValuesHolder */
        val holder = PropertyValuesHolder.ofFloat("TranslationX", 500f)
        val holder1 = PropertyValuesHolder.ofFloat("rotation", 360f)
        anim = ObjectAnimator.ofPropertyValuesHolder(iv, holder, holder1).apply {
            duration = 2000
        }

        /* PropertyValuesHolder with Keyframe */
        val k1 = Keyframe.ofFloat(0f, 0f)
        val k2 = Keyframe.ofFloat(0.2f, -20f)
        val k4 = Keyframe.ofFloat(0.6f, 20f)
        val k6 = Keyframe.ofFloat(1f, 0f)
        k6.interpolator = BounceInterpolator()

        val holder2 = PropertyValuesHolder.ofKeyframe("rotation", k1, k2, k4, k6)

        anim1 = ObjectAnimator.ofPropertyValuesHolder(iv1, holder2).apply {
            duration = 2000
        }

        val ke1 = Keyframe.ofObject(0f, 'A')
        val ke2 = Keyframe.ofObject(0.1f, 'K')
        val ke3 = Keyframe.ofObject(1f, 'Z')

        val holder3 = PropertyValuesHolder.ofKeyframe("", ke1, ke2, ke3).apply {
            setEvaluator(UIInterpolatorEvaluatorFragment.CharEvaluator())
        }
        ValueAnimator.ofPropertyValuesHolder(holder3).apply {
            anim2 = this
            duration=6000
            addUpdateListener {
                tv.text="${it.animatedValue as Char}"
            }
        }


    }

    override fun onResume() {
        super.onResume()
        anim.start()
        anim1.start()
        anim2.start()
    }

    override fun onPause() {
        super.onPause()
        anim.cancel()
        anim1.cancel()
        anim2.start()
    }
}