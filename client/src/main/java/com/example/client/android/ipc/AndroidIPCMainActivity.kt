package com.example.client.android.ipc

import android.os.Bundle
import com.example.client.R
import com.example.client._base.BaseActivity
import com.example.client._base.Entry
import com.example.client.android.ipc.binder.BinderActivity
import com.example.client.android.ipc.messenger.MessengerActivity
import kotlinx.android.synthetic.main.activity_main.*

class AndroidIPCMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView(rv, listOf(
            Entry("1.Binder", BinderActivity::class.java),
            Entry("2.Messenger",MessengerActivity::class.java),
        ))
    }
}