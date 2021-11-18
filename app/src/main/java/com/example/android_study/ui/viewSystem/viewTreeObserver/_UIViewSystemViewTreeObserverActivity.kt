package com.example.android_study.ui.viewSystem.viewTreeObserver

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_rv.*


class _UIViewSystemViewTreeObserverActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        setRecyclerView(
            rv, listOf(
                Entry("1.WindowAttach", UIWindowAttachActivity::class.java),
                Entry("2.WindowFocus", UIWindowFocusActivity::class.java),
                Entry("3.GlobalFocus", UIGlobalFocusActivity::class.java),
                Entry("4.⭐GlobalLayout", UIGlobalLayoutActivity::class.java),
                Entry("5.⭐PreDraw", UIPreDrawActivity::class.java),
                Entry("6.⭐Draw", UIDrawActivity::class.java),
            )
        )
    }
}