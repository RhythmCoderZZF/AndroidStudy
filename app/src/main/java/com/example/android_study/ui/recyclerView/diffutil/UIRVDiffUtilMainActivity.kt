package com.example.android_study.ui.recyclerView.diffutil

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.diffutil.payload.UIRVDiffUtilPayloadFragment

/**
 * Author:create by RhythmCoder
 * Date:2021/3/13
 * Description:
 */
class UIRVDiffUtilMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post {
            setViewPagerFragment(window, listOf(
                    EntryF("1.理解payload", UIRVDiffUtilPayloadFragment())
            ))
        }
    }
}