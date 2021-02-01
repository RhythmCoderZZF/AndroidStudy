package com.example.android_study.android.fragment

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.fragment._lifecycle.FragmentDemoAy
import com.example.android_study.android.fragment.fragmentManager.FragmentManagerActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class FragmentMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_fragment_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.生命周期", FragmentDemoAy::class.java),
                Entry("2.创建管理fragment", FragmentManagerActivity::class.java)
        ))

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
}