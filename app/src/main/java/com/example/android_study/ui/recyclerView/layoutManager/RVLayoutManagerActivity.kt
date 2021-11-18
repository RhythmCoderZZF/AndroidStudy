package com.example.android_study.ui.recyclerView.layoutManager

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui.recyclerView.layoutManager.custom_layoutManager.CustomLayoutManagerFragment
import com.example.android_study.ui.recyclerView.layoutManager.custom_recycle_all2_layoutManager.CustomLayoutManagerRecycleAll2Fragment
import com.example.android_study.ui.recyclerView.layoutManager.custom_recycle_all_layoutManager.CustomLayoutManagerRecycleAllFragment
import com.example.android_study.ui.recyclerView.layoutManager.custom_recycle_layoutManager.CustomLayoutManagerRecycleFragment

class RVLayoutManagerActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_vp

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setViewPagerFragment(
            window, listOf(
                EntryF("1.简单无回收机制的LayoutManager", CustomLayoutManagerFragment()),
                EntryF("2.能上滑回收复用的LayoutManager", CustomLayoutManagerRecycleFragment()),
                EntryF("3.完整回收复用的LayoutManager", CustomLayoutManagerRecycleAllFragment()),
                EntryF("4.完整回收复用的LayoutManager（2）", CustomLayoutManagerRecycleAll2Fragment()),
            )
        )
    }
}