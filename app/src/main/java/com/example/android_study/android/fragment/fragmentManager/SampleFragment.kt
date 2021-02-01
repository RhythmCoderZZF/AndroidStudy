package com.example.android_study.android.fragment.fragmentManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.BaseFragment
import com.example.android_study.databinding.FragmentSampleBinding

class SampleFragment : BaseFragment() {
    private var param1: String? = null
    private var binding: FragmentSampleBinding? = null

    init {
        showLifecycle = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        param1?.let {
            binding?.text?.text = it
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.getString(ARG_PARAM1)?.let {
            (requireActivity() as BaseActivity).showToast(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ARG_PARAM1, "屏幕旋转")
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String) =
                SampleFragment().apply {
                     param1
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                    }
                }
    }
}