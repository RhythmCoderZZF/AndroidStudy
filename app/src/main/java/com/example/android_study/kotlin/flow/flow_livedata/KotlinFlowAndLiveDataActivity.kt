package com.example.android_study.kotlin.flow.flow_livedata

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_flow_and_live_data.*

class KotlinFlowAndLiveDataActivity : BaseActivity() {

    override fun getLayoutId()=R.layout.activity_flow_and_live_data

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        button.setOnClickListener {
            startActivity(Intent(this,KotlinFlowAndLiveDataSecondActivity::class.java))
        }
        VM.observableInt.observe(this){
            textView.text="$it"
            CmdUtil.v((it.toString()))
        }
    }
}