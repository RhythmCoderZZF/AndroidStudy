package com.example.android_study.ui_custom.anim.anim

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.util.LogUtil
import kotlinx.android.synthetic.main.fragment_u_i_anim_base_anim_list_code.*
import java.lang.RuntimeException

class UIABaseAnimListCodeFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_base_anim_list_code, container, false)
    }

    override fun onResume() {
        super.onResume()
        val anim = AnimationDrawable()
        iv.setImageDrawable(anim)
        anim.isOneShot = false
        repeat(4) {
            val drawable = when (it) {
                0 -> ContextCompat.getDrawable(requireContext(), resources.getIdentifier("ic_light_rain", "drawable", requireContext().packageName))
                1 -> ContextCompat.getDrawable(requireContext(), resources.getIdentifier("ic_moderate_rain", "drawable", requireContext().packageName))
                2 -> ContextCompat.getDrawable(requireContext(), resources.getIdentifier("ic_heavy_rain", "drawable", requireContext().packageName))
                3 -> ContextCompat.getDrawable(requireContext(), resources.getIdentifier("ic_storm_rain", "drawable", requireContext().packageName))
                else -> throw  RuntimeException()
            }
            anim.addFrame(drawable!!, 100)
        }
        anim.start()
        LogUtil.d("zzz", requireContext().packageName)
    }

    override fun onPause() {
        super.onPause()
        (iv.drawable as AnimationDrawable).stop()
    }

}