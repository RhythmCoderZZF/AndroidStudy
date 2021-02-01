package com.example.android_study.jetpack.viewmodel.viewmodel

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.ToolbarHelper
import kotlinx.android.synthetic.main.activity_viewmodel_ay.*

/**
 * ViewModel总结：
 * 1. ViewModel是单例，UI layer公用同一个viewModel
 * 2. ViewModel的生存周期很长，当Activity/Fragment被异常destroy（屏幕旋转，系统杀死）时ViewModel也不会调用destroy
 */
class ViewModelAy : BaseActivity() {
    init {
        showLifecycle = true
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModel1: MainViewModel
    //懒汉式初始化
    private val viewModel2 by viewModels<MainViewModel> {
        MainViewModelFactory(5)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_viewmodel_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        ToolbarHelper.setBar(this, "ViewModel")
        CmdUtil.showWindow()
        //最先创建
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

    fun ViewModelFactory2(view: View) {
        viewModel2.counter += 1
        button2.text = "${viewModel2.counter}"
    }
}
