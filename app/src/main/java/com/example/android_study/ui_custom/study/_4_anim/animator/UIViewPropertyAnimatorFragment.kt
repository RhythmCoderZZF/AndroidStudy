package com.example.android_study.ui_custom.study._4_anim.animator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator_view_property_animator.*

class UIViewPropertyAnimatorFragment : BaseFragment() {
    private lateinit var anim:ViewPropertyAnimator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator_view_property_animator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onResume() {
        super.onResume()
        anim()
    }
    private fun anim() {
        anim=iv.animate().apply {
            duration=1000
//            interpolator=LinearInterpolator()
        }.rotationXBy(72f).withEndAction {//by：叠加动画
            anim()
        }
    }

    override fun onPause() {
        super.onPause()
        anim.cancel()
    }

}