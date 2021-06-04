package com.example.android_study.android.ipc.binder

import aidl.ILoginInterface
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import com.example.android_study._base.util.LogUtil
import java.lang.RuntimeException

class LoginService : Service() {
    private val handler = Handler()
    override fun onBind(intent: Intent): IBinder {
        return object : ILoginInterface.Stub() {
            override fun login(): Int {
                LogUtil.d("xxx", "server:login")
                return startLoginActivity()
            }
        }
    }

    private fun startLoginActivity(): Int {
        handler.post {
            startActivity(Intent(this@LoginService, IPCAIDLActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            })
            Toast.makeText(this, "成果调用Server!", Toast.LENGTH_SHORT).show()
        }

        handler.postDelayed({
            throw RuntimeException()
        }, 1000)
        return 1
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil.d("xxx", "server:create()")
    }
}