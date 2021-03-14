package com.example.android_study.ui_custom.study._4_anim.svg

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.umeng.vt.diff.V
import kotlinx.android.synthetic.main.fragment_u_i_anim_svg.iv

class UIAnimVectorFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_svg_anim, container, false)
    }

    override fun onResume() {
        super.onResume()
        (iv.drawable as AnimatedVectorDrawable).start()
    }

    override fun onPause() {
        super.onPause()
        (iv.drawable as AnimatedVectorDrawable).stop()
    }

}