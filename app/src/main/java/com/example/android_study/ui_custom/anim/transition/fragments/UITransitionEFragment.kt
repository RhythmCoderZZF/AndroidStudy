package com.example.android_study.ui_custom.anim.transition.fragments

import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.*
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_transition.btn
import kotlinx.android.synthetic.main.fragment_u_i_anim_transition_e.*

class UITransitionEFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_transition_e, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val originSize=element.layoutParams.width
        /*1. 根布局*/
        val rootView = rootView
        /*2. 定义过渡*/
        val transition = ChangeBounds()

        btn.setOnClickListener {
            /*3. 调用该方法时，立即捕获当前元素属性值，在下一帧到来时提交一个过渡任务。
            下一帧到来的时候会获取到新的属性值，过渡任务会自动触发旧值到新值之间的过渡*/
            TransitionManager.beginDelayedTransition(rootView, transition)
            val layoutParams = element.layoutParams as ConstraintLayout.LayoutParams
            if (layoutParams.leftMargin == 0) {
                layoutParams.leftMargin=600
                layoutParams.topMargin=1300
                layoutParams.width=300
                layoutParams.height=300
            }else{
                layoutParams.leftMargin=0
                layoutParams.topMargin=0
                layoutParams.width=originSize
                layoutParams.height=originSize
            }
            element.layoutParams=layoutParams
        }
    }

}