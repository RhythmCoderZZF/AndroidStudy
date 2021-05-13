package com.example.android_study.ui_custom.anim.viewGroup_anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_anim_view_group_layout_change.*

class UILayoutChangeAnimFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_anim_view_group_layout_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAdd.setOnClickListener {
            val img = ImageView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                setImageResource(R.mipmap.ic_launcher_round)
            }
            ll.addView(img,0)
        }
        btnMinus.setOnClickListener {
            if (ll.childCount > 0) {
                ll.removeViewAt(0)
            }
        }
    }

}