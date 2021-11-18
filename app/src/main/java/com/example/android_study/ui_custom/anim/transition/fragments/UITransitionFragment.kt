package com.example.android_study.ui_custom.anim.transition.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.*
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_transition.*

class UITransitionFragment : BaseFragment() {
    var enter = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_transition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*1. 根布局*/
        val sceneRoot = root
        /*2. 场景A*/
        val sceneA = Scene.getSceneForLayout(
            sceneRoot,
            R.layout.fragment_u_i_anim_transition_scene_a,
            requireContext()
        )
        /*2. 场景B*/
        val sceneB = Scene.getSceneForLayout(
            sceneRoot,
            R.layout.fragment_u_i_anim_transition_scene_b,
            requireContext()
        )
        /*3. 定义过渡*/
        val transition = Slide()
        /*4. 触发过渡*/
        btn.setOnClickListener {
            enter = !enter
            if (enter) {
                TransitionManager.go(sceneA, transition)
            } else {
                TransitionManager.go(sceneB, transition)
            }
        }
    }
}