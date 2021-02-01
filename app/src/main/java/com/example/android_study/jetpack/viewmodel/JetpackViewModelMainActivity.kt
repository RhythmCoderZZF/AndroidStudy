package com.example.android_study.jetpack.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.jetpack.viewmodel.isInstance.ViewModelTestActivity
import com.example.android_study.jetpack.viewmodel.viewmodel.ViewModelAy
import kotlinx.android.synthetic.main.activity_jetpack_view_model_main.*

class JetpackViewModelMainActivity : BaseActivity() {

    override fun getLayoutId()=R.layout.activity_jetpack_view_model_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.基础使用", ViewModelAy::class.java),
                Entry("2.viewModel是否单例?", ViewModelTestActivity::class.java)
        ))
    }
}