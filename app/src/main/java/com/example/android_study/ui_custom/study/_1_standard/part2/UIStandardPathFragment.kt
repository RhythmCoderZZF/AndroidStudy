package com.example.android_study.ui_custom.study._1_standard.part2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment

class UIStandardPathFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_standard_path, container, false)
    }

}