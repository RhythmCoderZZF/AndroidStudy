package com.example.android_study.jetpack.livedata

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_live_data_ay.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * LiveData具有生命感知，当Activity/Fragment被销毁的时候数据是无法传递到页面的
 */
class LiveDataAy : BaseActivity(), Runnable {
    private val count = MutableLiveData<Int>()//如果 LiveData 对象尚未设置初始值，则不会调用 onChanged()
    private val count1 = MutableLiveData<Int>()
    private val count2 = MutableLiveData<Int>()

    init {
        count1.value = 10
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_live_data_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        count.observe(this, { c ->
            textView.text = "$c"
            CmdUtil.i(c.toString())
        })
        count1.observe(this, { c ->
            textView1.text = "$c"
        })

        //粘性观察
        var lazyCount=0
        btnStickObserve.setOnClickListener {
            count2.value=++lazyCount
            if (lazyCount ==3) {
                count2.observe(this){
                    btnStickObserve.text=it.toString()
                }
            }
        }
    }

    fun button(view: View) {
        if (count.value == null) {
            count.value = 0
        }
        count.value = count.value as Int + 1
    }

    fun button1(view: View) {
        launch(IO) {
            while (true) {
                count1.postValue(count1.value as Int + 1)
                delay(500)
                CmdUtil.v(count1.value.toString())
            }
        }
    }

}