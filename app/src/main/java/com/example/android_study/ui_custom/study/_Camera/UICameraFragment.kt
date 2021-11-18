package com.example.android_study.ui_custom.study._Camera

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_u_i_cus_camera.*
import kotlinx.android.synthetic.main.fragment_u_i_cus_matrix.*
import java.util.*

class UICameraFragment : BaseFragment() {

    private lateinit var mAnim: ObjectAnimator
    private lateinit var mAnim1: ObjectAnimator
    private lateinit var mAnim2: ObjectAnimator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAnim1 = ObjectAnimator.ofFloat(img1, "rotationX", 0f, 360f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
        }
        mAnim = ObjectAnimator.ofFloat(img2, "rotationY", 0f, 360f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
        }

        mAnim2 = ObjectAnimator.ofFloat(img3, "rotation", 0f, 360f).apply {
            duration = 5000
            repeatCount = ObjectAnimator.INFINITE
        }
    }

    override fun onResume() {
        super.onResume()
        mAnim.start()
        mAnim1.start()
        mAnim2.start()
    }

    override fun onPause() {
        super.onPause()
        mAnim.cancel()
        mAnim1.cancel()
        mAnim2.cancel()
    }
}