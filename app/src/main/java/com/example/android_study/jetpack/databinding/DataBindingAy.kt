package com.example.android_study.jetpack.databinding

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.databinding.ActivityDatabindingAyBinding

/**
 * 数据绑定库是一种支持库，借助该库，您可以使用声明性格式（而非程序化地）将布局中的界面组件(View)绑定到应用中的数据源(Model)。
 */
class DataBindingAy : BaseActivity() {
    //开启DataBinding会生成layout id + Binding的class
    private lateinit var binding: ActivityDatabindingAyBinding

    override fun getLayoutId() = R.layout.activity_databinding_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_ay)

        binding.flag = false

        //注意!：User所处的包不能以 _开头
        val user = User("Tom", 18)
        binding.user = user

        //注意：flag不能使用包装类Boolean，而是boolean
        binding.button5.setOnClickListener { binding.flag = !(binding.flag) }
    }
}