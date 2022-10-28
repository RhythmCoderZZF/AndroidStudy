package com.example.android_study.performance.anr

import android.os.Bundle
import android.util.Log
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_performance_anr.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Author:create by RhythmCoder
 * Date:2021/7/28
 * Description:
 */
class PerformanceANRActivity : BaseActivity() {
    private val TAG = this.javaClass.simpleName
    private val SLEEP_TIME = 10_000L

    override fun getLayoutId() = R.layout.activity_performance_anr

    override fun initViewAndData(savedInstanceState: Bundle?) {
        /* 1.线程阻塞 */
        btn_block.setOnClickListener {
            Log.d(TAG,"1.线程阻塞 ")
            Thread.sleep(SLEEP_TIME)
        }

        /* 2.线程死锁 */
        btn_block1.setOnClickListener {
            launch {
                delay(100)
                Log.d(TAG,"2.线程死锁 ")
                synchronized(this@PerformanceANRActivity) {

                }
            }
            launch(IO) {
                synchronized(this@PerformanceANRActivity) {
                    Thread.sleep(SLEEP_TIME)
                }
            }
        }
    }
}