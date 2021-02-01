package com.example.android_study.ui.viewpager2.fragmentLifecycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study._base.BaseFragment
import com.example.android_study.databinding.FragmentViewPagerContentBinding

class ViewPagerContentFragment : BaseFragment() {
    private var param1: String = ""
    var binding: FragmentViewPagerContentBinding? = null

    init {
        showLifecycle = true
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentViewPagerContentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.textView.text = param1
    }

    companion object {

        fun newInstance(param: String) =
                ViewPagerContentFragment().apply {
                    param1 = param
                }
    }
}