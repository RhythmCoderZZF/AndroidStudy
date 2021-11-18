package com.example.android_study.ui.recyclerView.itemTouchHelper

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.adapter.EntryF
import com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_meet.ItemDecorationFragment
import com.example.android_study.ui.recyclerView.itemDecoration.itemDecoration_simple.ItemDecorationSimpleFragment
import com.example.android_study.ui.recyclerView.itemTouchHelper.childDrarw.RVItemTouchHelperChildDrawActivity
import com.example.android_study.ui.recyclerView.itemTouchHelper.meet.RVItemTouchHelperMeetActivity
import com.example.android_study.ui.recyclerView.itemTouchHelper.pointDrag.RVItemTouchHelperPointDragActivity
import kotlinx.android.synthetic.main.activity_rv.*

class RVItemTouchHelperMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
       setRecyclerView(rv, listOf(
           Entry("1.初识ItemTouchHelper",RVItemTouchHelperMeetActivity::class.java),
           Entry("2.限制拖拽", RVItemTouchHelperPointDragActivity::class.java),
           Entry("3.修改默认拖拽、侧滑样式", RVItemTouchHelperChildDrawActivity::class.java),
       ))
    }
}