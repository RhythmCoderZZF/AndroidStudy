package com.example.android_study.ui_custom.demo.standard.region_anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_s_d_region_anim.*

class UISDRegionAnimFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_s_d_region_anim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content.text="""
            1. 由于canvas.clipRegion被废弃，所以使用clipPath来实现clipRegion的效果
            2. 该demo并非由动画实现，是通过在path中不断增大rect的宽度来实现
        """.trimIndent()
    }

    override fun onResume() {
        super.onResume()
        vRegion.start()
    }

}