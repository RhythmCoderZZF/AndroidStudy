package com.example.android_study.ui_custom.anim.viewGroup_anim

import android.animation.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_view_group_layout_transiton.*

class UILayoutTransitionFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_view_group_layout_transiton, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val holder = PropertyValuesHolder.ofFloat("rotationX", 0f, 360f)
        val holder1 = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f)
        val holder2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f)
        val anim = ObjectAnimator.ofPropertyValuesHolder("", holder, holder1, holder2)
        val anim1 = ObjectAnimator.ofFloat("", "translationX", 0f, 180f).apply {
            interpolator = BounceInterpolator()
        }


        val transition = LayoutTransition().apply {
            setAnimator(LayoutTransition.APPEARING, anim)
            setAnimator(LayoutTransition.DISAPPEARING, anim1)
        }
        ll.layoutTransition = transition

        btnAdd.setOnClickListener {
            val img = ImageView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                setImageResource(R.mipmap.ic_launcher_round)
            }
            ll.addView(img, 0)
        }
        btnMinus.setOnClickListener {
            if (ll.childCount > 0) {
                ll.removeViewAt(0)
            }
        }
    }

}