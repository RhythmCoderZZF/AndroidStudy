package com.example.android_study.android.bluetooth

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.data_and_file._ContentProvider.AndroidContentProviderMainActivity
import com.example.android_study.android.data_and_file.internal_and_outspace.AndroidDataAndFileIOSpaceActivity
import com.example.android_study.android.drawable_and_graph.drawable.AndroidDrawableActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidBluetoothMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.API", BluetoothApiActivity::class.java, "相关API"),
        ))

    }
}