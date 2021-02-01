package com.example.android_study.kotlin.flow.flow_livedata

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_flow_and_live_data_second.*
import kotlinx.android.synthetic.main.activity_flow_and_live_data_second.textView
import kotlin.random.Random

class KotlinFlowAndLiveDataSecondActivity : BaseActivity() {
    override fun getLayoutId()=R.layout.activity_flow_and_live_data_second

    override fun initViewAndData(savedInstanceState: Bundle?) {
        VM.observableInt.observe(this){
            textView.text="$it"
            CmdUtil.i((it.toString()))
        }
        button1.setOnClickListener {
            (VM.observableInt as MutableLiveData).postValue(Random.nextInt())
        }
    }
}