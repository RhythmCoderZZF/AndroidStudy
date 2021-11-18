package com.example.android_study.android.activity.fragment.communication.withViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_communication_viewmodel_a.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class FViewModelA : BaseFragment() {
    private val viewModel by activityViewModels<FragmentViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_communication_viewmodel_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                text.text = it
            }
        }
        text.setOnClickListener {
            parentFragmentManager.commit {
                setCustomAnimations(
                    R.anim.fragment_in,
                    R.anim.fragment_out,
                    R.anim.fragment_in,
                    R.anim.fragment_out
                )
                setReorderingAllowed(true)
                addToBackStack(null)
                replace(R.id.container, FViewModelB::class.java, null)
            }
        }
    }

}