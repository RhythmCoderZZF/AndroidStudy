package com.example.android_study.ui_custom.study._Camera.flipImage

import android.animation.ObjectAnimator
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_cus_camera.*
import kotlinx.android.synthetic.main.fragment_u_i_cus_camera_flip_view.*
import kotlinx.android.synthetic.main.fragment_u_i_cus_matrix.*
import java.util.*

class UICameraFlipViewFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_camera_flip_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnFlip.setOnClickListener {
            myFlipView.flip()
        }
    }

}