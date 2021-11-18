package com.example.android_study.android.activity.fragment.communication.withResultApi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study.android.activity.fragment.SampleFragment
import kotlinx.android.synthetic.main.fragment_communication_viewmodel_b.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class FResultB : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_communication_viewmodel_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener {
            setFragmentResult("key", bundleOf("result" to edt.text.toString()))
            parentFragmentManager.popBackStack()
        }
    }
}