package com.example.android_study.ui_custom.anim.animator

import android.animation.TimeInterpolator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator_interpolator_evaluator.*

class UIInterpolatorEvaluatorFragment : BaseFragment() {
    private lateinit var anim: ValueAnimator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator_interpolator_evaluator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anim = ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z').apply {
            interpolator = MyInterpolator()
            duration = 5000
        }
        anim.addUpdateListener {
            val value = it.animatedValue as Char
            tv.text = "$value"
        }
    }

    override fun onResume() {
        super.onResume()
        anim.start()
    }

    override fun onPause() {
        super.onPause()
        anim.cancel()
    }

    class CharEvaluator : TypeEvaluator<Char> {
        override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
            val startV = startValue.toInt()
            val endV = endValue.toInt()
            return (startV + fraction * (endV - startV)).toChar()
        }
    }

    class MyInterpolator : TimeInterpolator {
        override fun getInterpolation(input: Float): Float {
            CmdUtil.v("$input")
            return input
        }
    }
}