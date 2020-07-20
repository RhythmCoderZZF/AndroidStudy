package com.example.android_study._jetpack._livedata

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.example.android_study.R
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_live_data_ay.*

/**
 * LiveData具有生命感知，当activity被销毁的时候数据是无法传递到页面的
 */
class LiveDataAy : BaseActivity() {
    private val count = MutableLiveData<Int>()
    private val count1 = MutableLiveData<Int>()

    init {
        count.value = 0
        count1.value = 100
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_live_data_ay
    }

       override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        count.observe(this) { c -> textView.text = "$c" }
        count1.observe(this) { c ->
            textView1.text = "$c"
            CmdUtil.v("$c")
        }
    }

    fun button(view: View) {
        count.value = count.value as Int + 1
    }

    fun button1(view: View) {
        Thread(Runnable {
            Thread.sleep(2000)
            count1.postValue(count1.value as Int + 1)
        }).start()
    }

}