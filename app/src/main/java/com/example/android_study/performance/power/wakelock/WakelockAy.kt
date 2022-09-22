package com.example.android_study.performance.power.wakelock

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlin.concurrent.thread

class WakelockAy : BaseActivity() {
    @Volatile
    private var exit = false
    private lateinit var mPowerManager: PowerManager
    private lateinit var mWakeLock: PowerManager.WakeLock

    override fun getLayoutId() = R.layout.activity_performance_wakelock

    @SuppressLint("InvalidWakeLockTag")
    override fun initViewAndData(savedInstanceState: Bundle?) {
        mPowerManager = mContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        mWakeLock =
            mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Android_Study test WakeLock")
        acquire()
    }

    private fun acquire() {
        mWakeLock.acquire()

        thread {
            while (!exit) {
                Log.d("WakelockAy-111", "${System.currentTimeMillis()}")
                Thread.sleep(10)
            }
        }
        thread {
            while (!exit) {
                Log.d("WakelockAy-222", "${System.currentTimeMillis()}")
                Thread.sleep(10)
            }
        }
        thread {
            while (!exit) {
                Log.d("WakelockAy-333", "${System.currentTimeMillis()}")
                Thread.sleep(10)
            }
        }
        thread {
            while (!exit) {
                Log.d("WakelockAy-444", "${System.currentTimeMillis()}")
                Thread.sleep(10)
            }
        }
        thread {
            while (!exit) {
                Log.d("WakelockAy-555", "${System.currentTimeMillis()}")
                Thread.sleep(10)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exit = true
        release()
    }

    private fun release() {
        if (mWakeLock.isHeld) {
            mWakeLock.release()
        }
    }
}