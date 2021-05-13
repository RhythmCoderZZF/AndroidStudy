package com.example.android_study.ui_custom.anim.animator

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_value_animator_xml.*


/**
 * Author:create by RhythmCoder
 * Date:2021/2/19
 * Description:
 */
class UIXmlFragment : BaseFragment() {
    private lateinit var anim: ValueAnimator
    private lateinit var anim1: ObjectAnimator
    private lateinit var anim2: AnimatorSet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_value_animator_xml, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* rotate */
        anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.ui_cus_rotate) as ValueAnimator
        anim.addUpdateListener {
            val value = it.animatedValue as Float
            iv.rotation = value
        }

        /* alpha */
        anim1 = AnimatorInflater.loadAnimator(requireContext(), R.animator.ui_cus_alpha) as ObjectAnimator
        anim1.target = iv1

        /* set */
        anim2 = AnimatorInflater.loadAnimator(requireContext(), R.animator.ui_cus_set) as AnimatorSet
        anim2.setTarget(iv2)

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
        anim2.cancel()
    }

}