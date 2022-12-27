package com.example.client

import android.os.Bundle
import com.example.client._base.BaseActivity
import com.example.client._base.Entry
import com.example.client.android.activity.AndroidActivity
import com.example.client.android.content_provider.AndroidContentProviderMainActivity
import com.example.client.android.ipc.AndroidIPCMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView(rv, listOf(
                Entry("1.ContentProvider",AndroidContentProviderMainActivity::class.java),
                Entry("2.IPC",AndroidIPCMainActivity::class.java),
                Entry("3.Activity启动模式", AndroidActivity::class.java),
        ))
    }
}