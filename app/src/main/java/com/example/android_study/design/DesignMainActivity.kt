package com.example.android_study.design

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.ipc.binder.IPCAIDLActivity
import com.example.android_study.android.ipc.messager.IPCMessengerActivity
import com.example.android_study.design._1_Singleton.DesignSingletonActivity
import kotlinx.android.synthetic.main.activity_rv.*

class DesignMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 单例", DesignSingletonActivity::class.java),
                Entry("2 Messenger", IPCMessengerActivity::class.java,"Messenger本质是封装Binder和Handler"),
        ))
    }
}