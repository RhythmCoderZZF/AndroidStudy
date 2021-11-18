package com.example.android_study.Java.Juc

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.Java.Juc._production_consumption.JucProductionConsumptionActivity
import com.example.android_study.Java.Juc.interrupt.InterruptActivity
import com.example.android_study.Java.Juc.join.JucJoinActivity
import com.example.android_study.Java.Juc.wait_notify.JucWaitNotifyActivity
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import kotlinx.android.synthetic.main.activity_rv.*

class JavaJucActivity : BaseActivity() {
    private val list = listOf(

        Entry("1. join之线程同步", JucJoinActivity::class.java),
        Entry("2. interrupt中断", InterruptActivity::class.java),
        Entry("3. wait notify特点", JucWaitNotifyActivity::class.java),
        Entry("4. Synchronize生产消费模型", JucProductionConsumptionActivity::class.java),
    )

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
    }
}