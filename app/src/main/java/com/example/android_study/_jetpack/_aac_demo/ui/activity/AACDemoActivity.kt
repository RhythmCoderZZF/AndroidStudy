package com.example.android_study._jetpack._aac_demo.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R
import com.example.android_study._jetpack._aac_demo.ui.viewmodel.MainViewModel
import com.example.android_study.base.BaseActivity

class AACDemoActivity : BaseActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    override fun getLayoutId(): Int {
        return R.layout.activity_a_a_c_demo
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
//        viewModel.banner.observe(this, Observer { result ->
//            val banner = result.getOrNull()
//            if (banner != null) {
//                showToast(banner.toString())
//            } else {
//                showToast(result.exceptionOrNull()?.printStackTrace().toString())
//            }
//
//        })
    }

//    fun onClick(view: View) {
//        viewModel.getBanner()
//    }


}