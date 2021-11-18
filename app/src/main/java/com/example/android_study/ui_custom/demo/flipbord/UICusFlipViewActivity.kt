package com.example.android_study.ui_custom.demo.flipbord

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui_custom.demo.flipbord.flipViewGroup.FragmentFlipViewGroup
import com.example.android_study.ui_custom.demo.flipbord.flip_page.FragmentTurnPage
import com.example.android_study.ui_custom.demo.flipbord.fragments.FragmentFlipView

class UICusFlipViewActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(
            window, listOf(
                EntryF("1.翻页效果FlipView", FragmentFlipView()),
                EntryF("2.自定义翻页控件FlipViewGroup", FragmentFlipViewGroup()),
                EntryF("3.TurnPage", FragmentTurnPage()),
            )
        )
    }

}