package com.example.android_study.ui_custom.study._SurfaceView

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.dp2px
import kotlinx.android.synthetic.main.fragment_u_i_cus_bitmap.*
import kotlin.math.abs

class UISurfaceViewFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_surface_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}