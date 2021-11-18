package com.example.android_study.ui.recyclerView

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.diffutil.UIRVDiffUtilMainActivity
import com.example.android_study.ui.recyclerView.diffutil.UIRecyclerViewDiffUtilActivity
import com.example.android_study.ui.recyclerView.gesture.UIRecyclerViewDragActivity
import com.example.android_study.ui.recyclerView.itemDecoration.RVItemDecorationActivity
import com.example.android_study.ui.recyclerView.itemTouchHelper.RVItemTouchHelperMainActivity
import com.example.android_study.ui.recyclerView.layoutManager.RVLayoutManagerActivity
import com.example.android_study.ui.recyclerView.positioin.RVPositionMainActivity
import com.example.android_study.ui.recyclerView.section.UIRecyclerViewSectionActivity
import kotlinx.android.synthetic.main.activity_u_i_recycler_view_main.*

class UIRecyclerViewMainActivity : BaseActivity() {
    private val list = listOf(
            Entry("1.Item拖拽", UIRecyclerViewDragActivity::class.java),
            Entry("2.DiffUtil",UIRVDiffUtilMainActivity::class.java),
            Entry("3.分组",UIRecyclerViewSectionActivity::class.java),
            Entry("4.ItemDecoration",RVItemDecorationActivity::class.java),
            Entry("5.自定义LayoutManager",RVLayoutManagerActivity::class.java),
            Entry("6.ItemTouchHelper",RVItemTouchHelperMainActivity::class.java),
            Entry("7.Position相关", RVPositionMainActivity::class.java),

    )


    override fun getLayoutId() = R.layout.activity_u_i_recycler_view_main


    override fun initViewAndData(savedInstanceState: Bundle?) {
       setRecyclerView(rv,list)
        CmdUtil.showWindow()
    }


}