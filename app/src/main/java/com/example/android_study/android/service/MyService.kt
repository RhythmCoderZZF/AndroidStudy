package com.example.android_study.android.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.android_study._base.cmd.CmdUtil

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        CmdUtil.v("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CmdUtil.v("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        CmdUtil.v("onDestroy")
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        val service = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        CmdUtil.v("onBind")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        CmdUtil.v("onUnbind")
        return super.onUnbind(intent)
    }
}