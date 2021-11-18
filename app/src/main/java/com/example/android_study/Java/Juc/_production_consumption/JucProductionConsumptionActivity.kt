package com.example.android_study.Java.Juc._production_consumption

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_juc_production_consumption.*
import kotlin.concurrent.thread

class JucProductionConsumptionActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_juc_production_consumption

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val o =
            SyncTicket()
        button.setOnClickListener {
            for (i in 0..3) {
                thread(start = true, name = "A${i}") {
                    Thread.sleep(1000)
                    o.increment()
                }
            }
            for (i in 0..1) {
                thread(start = true, name = "B${i}") {
                    Thread.sleep(3000)
                    o.decrement()
                }
            }
            button1.setOnClickListener {

            }

        }
    }
}
