package com.example.android_study.ui_custom.anim.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.view.animation.Animation.RELATIVE_TO_PARENT
import android.view.animation.Animation.RELATIVE_TO_SELF
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_base_code.*

class UIABaseCodeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_base_code, container, false)
    }


    override fun onResume() {
        super.onResume()
        val anim1=ScaleAnimation(1f,2f,1f,2f,RELATIVE_TO_SELF,0.5f,RELATIVE_TO_SELF,0.5f)
        val anim2 = RotateAnimation(0f, 720f, RELATIVE_TO_PARENT, 0.5f, RELATIVE_TO_PARENT, 0.0f)
        val set = AnimationSet(false)
        set.apply {
            addAnimation(anim1)
            addAnimation(anim2)

            duration=2000
            fillAfter=true
        }
        iv.startAnimation(set)
    }
}