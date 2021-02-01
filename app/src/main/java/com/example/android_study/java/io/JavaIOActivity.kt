package com.example.android_study.java.io

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.java.juc._production_consumption.JucProductionConsumptionActivity
import com.example.android_study.java.juc.interrupt.InterruptActivity
import com.example.android_study.java.juc.join.JucJoinActivity
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.java.io.file.JavaIOFileActivity
import kotlinx.android.synthetic.main.activity_java_juc.*

class JavaIOActivity : BaseActivity() {
    private val list = listOf(
            Entry("1. separator和pathSeparator", JavaIOFileActivity::class.java),
    )

    override fun getLayoutId() = R.layout.activity_java_io

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
    }
}