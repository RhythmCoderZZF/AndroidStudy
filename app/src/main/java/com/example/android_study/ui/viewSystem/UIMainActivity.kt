package com.example.android_study.ui.viewSystem

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui.viewSystem.choreographer.UIViewSystemChoreographerActivity
import com.example.android_study.ui.viewSystem.viewTreeObserver._UIViewSystemViewTreeObserverActivity
import com.example.android_study.ui.viewSystem.view_invalidate_requestLayout._UIViewSystemViewIRActivity
import com.example.android_study.ui.viewSystem.view_post._UIViewSystemViewPostActivity
import com.example.android_study.ui.viewSystem.window.UiWindowActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UIMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1 WindowManager", UiWindowActivity::class.java,"利用WindowManager创建视图"),
                Entry("2 Choreographer", UIViewSystemChoreographerActivity::class.java),
                Entry("3 ViewTreeObserver", _UIViewSystemViewTreeObserverActivity::class.java),
                Entry("4 View.post源码", _UIViewSystemViewPostActivity::class.java),
                Entry("5 Invalidate和RequestLayout", _UIViewSystemViewIRActivity::class.java),
            )
        )
    }
}