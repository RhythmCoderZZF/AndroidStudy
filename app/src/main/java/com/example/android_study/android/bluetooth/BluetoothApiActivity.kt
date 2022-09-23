package com.example.android_study.android.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.*
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_bluetooth_api.*
import java.lang.reflect.Method

class BluetoothApiActivity : BaseActivity() {
    private val mReceiver = BTReceiver()
    private val mBTAdapter = BluetoothAdapter.getDefaultAdapter()


    private class BTReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                when (it.action) {
                    BluetoothDevice.ACTION_ACL_CONNECTED -> {
                        CmdUtil.v("ACTION_ACL_CONNECTED")
                    }
                    BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED -> {
                        CmdUtil.v("ACTION_ACL_DISCONNECT_REQUESTED")
                    }
                    BluetoothDevice.ACTION_ACL_DISCONNECTED -> {
                        CmdUtil.v("ACTION_ACL_DISCONNECTED")
                    }
                    BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED -> {
                        CmdUtil.i("ACTION_CONNECTION_STATE_CHANGED")
                    }
                    BluetoothAdapter.ACTION_STATE_CHANGED -> {
                        when (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0)) {
                            BluetoothAdapter.STATE_TURNING_ON ->
                                CmdUtil.i("STATE_TURNING_ON")
                            BluetoothAdapter.STATE_ON ->
                                CmdUtil.i("STATE_ON")
                            BluetoothAdapter.STATE_TURNING_OFF ->
                                CmdUtil.i("STATE_TURNING_OFF")
                            BluetoothAdapter.STATE_OFF ->
                                CmdUtil.i("STATE_OFF")
                        }
                    }
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.activity_android_bluetooth_api

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        btn_register.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val filter = IntentFilter()
                filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED)
                filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
                filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
                filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
                filter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
                filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
                registerReceiver(mReceiver, filter)
            } else {
                unregisterReceiver(mReceiver)
            }
        }

        btn_get_connect_state.setOnClickListener {
            try {
                val method: Method = BluetoothAdapter::class.java.getDeclaredMethod(
                    "getConnectionState",
                )
                method.isAccessible = true
                val stateStr = when (method.invoke(mBTAdapter) as Int) {
                    STATE_DISCONNECTED -> {
                        "STATE_DISCONNECTED"
                    }
                    STATE_CONNECTING -> {
                        "STATE_CONNECTING"
                    }
                    STATE_CONNECTED -> {
                        "STATE_CONNECTED"
                    }
                    STATE_DISCONNECTING -> {
                        "STATE_DISCONNECTING"
                    }
                    else -> {
                        "-1"
                    }
                }
                CmdUtil.v("$stateStr")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
    }
}