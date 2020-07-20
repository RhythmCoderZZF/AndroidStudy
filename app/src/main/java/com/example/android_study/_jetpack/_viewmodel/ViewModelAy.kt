package com.example.android_study._jetpack._viewmodel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R
import com.example.android_study._jetpack._viewmodel.viewmodel.MainViewModel
import com.example.android_study._jetpack._viewmodel.viewmodel.MainViewModelFactory
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import com.example.android_study.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity_viewmodel_ay.*

class ViewModelAy : BaseActivity() {
    init {
        showLifecycle=true
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel1: MainViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_viewmodel_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this, "ViewModel")
        CmdUtil.showWindow()
        viewModel1 = ViewModelProvider(this, MainViewModelFactory(10)).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    fun ViewModel(view: View) {
        viewModel.counter += 1
        button.text = "${viewModel.counter}"
    }

    fun ViewModelFactory(view: View) {
        viewModel1.counter += 1
        button1.text = "${viewModel1.counter}"
    }
}