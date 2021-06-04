package com.example.android_study.android.ipc.messager

import android.app.Service
import android.content.Intent
import android.os.*
import com.example.android_study._base.cmd.CmdUtil

class MessengerService : Service() {
    private var mClientMessenger: Messenger? = null

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    mClientMessenger = msg.replyTo
                    CmdUtil.i("Server收到Client的Messenger")
                }
                1 -> {
                    val d = msg.data.get("client_data")
                    mClientMessenger?.send(Message.obtain().apply {
                        data = Bundle().apply {
                            putString("server_data", "来自Server的回复:${d}")
                        }
                    })
                }
            }
        }
    }
    private val mServerMessenger = Messenger(handler)

    override fun onBind(intent: Intent): IBinder {
        return mServerMessenger.binder
    }

    override fun onCreate() {
        super.onCreate()
        CmdUtil.showWindow()
    }
}