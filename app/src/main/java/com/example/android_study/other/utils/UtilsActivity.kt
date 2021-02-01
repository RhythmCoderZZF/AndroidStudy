package com.example.android_study.other.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android_study.R
import com.example.android_study.databinding.ActivityUtilsBinding

class UtilsActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityUtilsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_utils)
        dataBinding.str="请查阅${this.javaClass.`package`}包下相关的工具类"
    }
}