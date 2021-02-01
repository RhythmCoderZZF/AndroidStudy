package com.example.android_study.android._database._sp

import android.content.Context
import android.os.Bundle
import androidx.core.content.edit
import com.example.android_study.R
import com.example.android_study._base.BaseActivity

class SpAy : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_sp_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        getSharedPreferences("string", Context.MODE_PRIVATE).edit(commit = false, action = {
            val start = System.currentTimeMillis()
            repeat(1000000) {
                putString("hello${it}", "123")
            }
            val end = System.currentTimeMillis()
            showToast("${end - start} ")

        })
    }

}