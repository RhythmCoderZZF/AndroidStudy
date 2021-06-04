package com.example.client.android.ipc.binder

import aidl.ILoginInterface
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.client.R
import com.example.client._base.BaseActivity
import com.example.client._base.LogUtil
import kotlinx.android.synthetic.main.activity_android_ipc.*

class BinderActivity : BaseActivity() {
    private var loginManager: ILoginInterface? = null
    private lateinit var death: IBinder.DeathRecipient

    init {
        death = IBinder.DeathRecipient {
            LogUtil.e("xxx", "binder死亡！")
            loginManager?.let {
                it.asBinder().unlinkToDeath(death, 0)//移除监听
                loginManager = null
            }
        }
    }

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            //2.将binder转成LoginInterface
            loginManager = ILoginInterface.Stub.asInterface(service).also {
                it.asBinder().linkToDeath(death,0)//绑定死亡监听
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_ipc)
        //1.连接远程Service
        initBindService()
        //3.调用LoginInterface去登陆
        login.setOnClickListener {
            toWeChatLogin()
        }
    }

    private fun initBindService() {
        val intent = Intent().apply {
            action = "aidl_login_action"
            `package` = "com.example.android_study"
        }
        bindService(intent, conn, BIND_AUTO_CREATE)
    }

    private fun toWeChatLogin() {
        loginManager?.let {
            LogUtil.d("xxx", "client:登陆")
            val status = it.login()
            LogUtil.d("xxx", "client登陆结果:$status")
            return
        }
        Toast.makeText(this, "登陆连接服务未启动", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        conn?.let {
            unbindService(it)
        }
    }
}