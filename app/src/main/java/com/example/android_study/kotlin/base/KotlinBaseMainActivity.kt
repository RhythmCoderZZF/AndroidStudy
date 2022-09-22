package com.example.android_study.kotlin.base

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.base._1_array.KotlinArrayMainActivity
import com.example.android_study.kotlin.base._2_function.KotlinFunctionMainActivity
import com.example.android_study.kotlin.base._3_nullPointException.KotlinBaseNullPointMainActivity
import kotlinx.android.synthetic.main.activity_rv.*

class KotlinBaseMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.数组", KotlinArrayMainActivity::class.java, "初始化，遍历，区间"),
                Entry("2.函数", KotlinFunctionMainActivity::class.java, "函数类型，带Receiver的函数类型"),
                Entry("3.空指针", KotlinBaseNullPointMainActivity::class.java, "kotlin空安全"),
            )
        )
    }
}