package com.example.android_study.android.ipc

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.ipc.binder.IPCAIDLActivity
import com.example.android_study.android.ipc.messager.IPCMessengerActivity
import kotlinx.android.synthetic.main.activity_rv.*

class IPCMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 AIDL", IPCAIDLActivity::class.java,"一个aidl实现的binder通信机制,本app属于Service，请启动client app使用"),
                Entry("2 Messenger", IPCMessengerActivity::class.java,"Messenger本质是封装Binder和Handler"),
        ))
    }
}