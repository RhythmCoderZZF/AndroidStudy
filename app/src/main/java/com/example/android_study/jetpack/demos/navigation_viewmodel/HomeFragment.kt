package com.example.android_study.jetpack.demos.navigation_viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.android_study.R
import com.example.android_study.databinding.FragmentFirstBinding
import kotlinx.android.synthetic.main.fragment_first.*


class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentFirstBinding
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_first, container, false)
        dataBinding.viewModel=viewModel
        //设置lifecycleOwner用于观察viewModel liveData的数据改变
        dataBinding.lifecycleOwner=requireActivity()
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seekBar.progress = viewModel.count.value?:0
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.count.value=progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        button.setOnClickListener {
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_homeFragment_to_detailFragment)
        }
    }

}