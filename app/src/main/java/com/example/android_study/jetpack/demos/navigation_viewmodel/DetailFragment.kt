package com.example.android_study.jetpack.demos.navigation_viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android_study.R
import com.example.android_study.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_detail, container, false)
        dataBinding.viewModel=viewModel
        //设置lifecycleOwner用于观察viewModel liveData的数据改变
        dataBinding.lifecycleOwner=requireActivity()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button1.setOnClickListener {
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
}