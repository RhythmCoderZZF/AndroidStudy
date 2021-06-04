package com.example.client.android.ipc.messenger

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.Toast
import com.example.client.R
import com.example.client._base.BaseActivity
import kotlinx.android.synthetic.main.activity_android_ipc.*
import kotlinx.android.synthetic.main.activity_android_ipc_messenger.*

class MessengerActivity : BaseActivity() {
    private var mServerMessenger: Messenger? = null

    private var mHandler = Handler {
        Toast.makeText(this, "${it.data.get("server_data")}", Toast.LENGTH_SHORT).show()
        true
    }

    private val mClientMessenger = Messenger(mHandler)

    private var conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mServerMessenger = Messenger(service)
            val message = Message.obtain().apply {
                what = 0
                replyTo = mClientMessenger
            }
            mServerMessenger?.send(message)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_ipc_messenger)

        bind.setOnClickListener {
            bindService(Intent().apply {
                action = "aidl_messenger_action"
                `package` = "com.example.android_study"
            }, conn, BIND_AUTO_CREATE)
        }

        send.setOnClickListener {
            if (edt.text.isNullOrEmpty()) {
                Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            mServerMessenger?.send(Message.obtain().apply {
//                obj = edt.text.toString() 不要使用obj传递对象，无法序列化
                what = 1
                data = Bundle().apply {
                    putString("client_data", edt.text.toString())
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }
}