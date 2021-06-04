package com.example.android_study.ui.viewSystem

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui.viewSystem.choreographer.UIChoreographerActivity
import com.example.android_study.ui.viewSystem.windowManager.UIWindowManagerActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UIViewSystemActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1 WindowManager", UIWindowManagerActivity::class.java,"利用WindowManager创建视图"),
                Entry("2 Choreographer", UIChoreographerActivity::class.java),
            )
        )
    }
}