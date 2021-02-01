package com.example.android_study.jetpack.demos.viewmodel

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.databinding.ActivityScoreBoardAyBinding

class ScoreBoardAy : BaseActivity() {
    private lateinit var binding: ActivityScoreBoardAyBinding
    private lateinit var viewModel: ScoreBoardViewModel


    override fun getLayoutId() = R.layout.activity_score_board_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_score_board_ay)

        //注意：ViewModel使用到SavedState时需要配置SavedStateViewModelFactory
        viewModel = ViewModelProvider(this, SavedStateViewModelFactory(application, this)).get(ScoreBoardViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}