package com.example.android_study.android.activity.fragment.communication.withResultApi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_communication_viewmodel_a.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class FResultA : BaseFragment() {
    override var TAG = "FResultA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("key") { key, bundle ->
            val result = bundle.getString("result")
            if (result?.isNotEmpty() == true) {
                text.text = result
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_communication_viewmodel_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                replace(R.id.container1, FResultB::class.java, null)
            }
        }
    }

}