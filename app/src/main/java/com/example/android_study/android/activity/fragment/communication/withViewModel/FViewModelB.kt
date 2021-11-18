package com.example.android_study.android.activity.fragment.communication.withViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_communication_viewmodel_b.*


/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class FViewModelB : BaseFragment() {
    private val viewModel by activityViewModels<FragmentViewModel>()
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
            viewModel.data.value=edt.text.toString()
            parentFragmentManager.popBackStack()
        }
    }
}