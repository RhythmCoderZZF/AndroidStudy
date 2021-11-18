package com.example.android_study.android.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.BaseFragment
import com.example.android_study.databinding.FragmentSampleBinding

open class SampleFragment : BaseFragment() {
    override var TAG = t
    private var binding: FragmentSampleBinding? = null

    init {
        showLifecycle = true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            TAG = it.getString(ARG_PARAM1) ?: TAG
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.text?.text = TAG
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
        var t = "default"

        @JvmStatic
        fun newInstance(param1: String): Fragment {
            t = param1
            return SampleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
        }

    }
}