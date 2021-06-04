package com.example.android_study.ui_custom.anim.transition.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.*
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study.ui_custom.anim.transition._UITransitionShareElementActivity
import kotlinx.android.synthetic.main.fragment_u_i_anim_transition.btn
import kotlinx.android.synthetic.main.fragment_u_i_anim_transition_a.*


class UITransitionAFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_transition_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(requireActivity().window) {
            sharedElementEnterTransition =
                TransitionSet().addTransition(ChangeImageTransform()).addTransition(ChangeBounds())
            sharedElementExitTransition =
                TransitionSet().addTransition(ChangeImageTransform()).addTransition(ChangeBounds())
            enterTransition.excludeTarget(android.R.id.statusBarBackground, true)
            exitTransition.excludeTarget(android.R.id.statusBarBackground, true)
        }
        btn.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    _UITransitionShareElementActivity::class.java
                ), ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
        }
        btn1.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    _UITransitionShareElementActivity::class.java
                ),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity(), img, "img")
                    .toBundle()
            )
        }

    }

}