package com.example.android_study.ui_custom.anim.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_base_anim_list.*

class UIABaseAnimListXmlFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_base_anim_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        val anim = iv.drawable as AnimationDrawable
        anim.start()
    }

    override fun onPause() {
        super.onPause()
        val anim = iv.drawable as AnimationDrawable
        anim.stop()
    }
}