package com.example.android_study.ui_custom.anim.transition

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil

class _UITransitionShareElementActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_u_i_anim_transition_share_element

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        with(window) {
            sharedElementEnterTransition =
                TransitionSet().addTransition(ChangeImageTransform()).addTransition(ChangeBounds())
            sharedElementExitTransition =
                TransitionSet().addTransition(ChangeImageTransform()).addTransition(ChangeBounds())
            sharedElementEnterTransition.duration = 3000


            enterTransition.excludeTarget(android.R.id.statusBarBackground, true)
            exitTransition.excludeTarget(android.R.id.statusBarBackground, true)
        }
        CmdUtil.v("created")
    }

    override fun onDestroy() {
        super.onDestroy()
        CmdUtil.v("onDestroy")
    }
}