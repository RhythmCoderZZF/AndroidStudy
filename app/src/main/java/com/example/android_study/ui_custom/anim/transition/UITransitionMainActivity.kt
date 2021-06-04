package com.example.android_study.ui_custom.anim.transition

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.anim.transition.fragments.UITransitionAFragment
import com.example.android_study.ui_custom.anim.transition.fragments.UITransitionEFragment
import com.example.android_study.ui_custom.anim.transition.fragments.UITransitionFragment

class UITransitionMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {

        setViewPagerFragment(window, listOf(
                EntryF("1. 布局过渡", UITransitionFragment()),
                EntryF("2. 元素过渡", UITransitionEFragment()),
                EntryF("3. Activity过渡", UITransitionAFragment()),
        ))
    }
}