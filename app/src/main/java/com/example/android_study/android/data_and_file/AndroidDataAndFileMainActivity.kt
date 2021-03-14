package com.example.android_study.android.data_and_file

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.data_and_file.internal_and_outspace.AndroidDataAndFileIOSpaceActivity
import com.example.android_study.android.drawable_and_graph.drawable.AndroidDrawableActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidDataAndFileMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(rv, listOf(
                Entry("1.专属存储空间", AndroidDataAndFileIOSpaceActivity::class.java,"存储仅供应用使用的文件，可以存储到内部存储卷中的专属目录或外部存储空间中的其他专属目录。使用内部存储空间中的目录保存其他应用不应访问的敏感信息。"),
        ))

    }
}