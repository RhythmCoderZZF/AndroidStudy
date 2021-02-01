package com.example.android_study.android.service

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_service2.*


class ServiceFragment2 : Fragment() {
    private var myService: MyService? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myService = (service as MyService.MyBinder).service
            CmdUtil.i("onServiceConnected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            CmdUtil.i("onServiceDisconnected")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnBindService.apply {
            setOnClickListener {
                requireContext().bindService(Intent(requireContext(), MyService::class.java), serviceConnection, BIND_AUTO_CREATE)
            }
        }

        btnUnBindService.apply {
            setOnClickListener {
                requireContext().unbindService(serviceConnection)
            }
        }
    }
}