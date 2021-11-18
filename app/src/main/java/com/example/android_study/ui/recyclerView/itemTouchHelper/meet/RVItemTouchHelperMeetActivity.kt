package com.example.android_study.ui.recyclerView.itemTouchHelper.meet

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.StringAdapter
import kotlinx.android.synthetic.main.activity_android_drawable.*
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.android.synthetic.main.fragment_service2.view.*
import java.util.*

class RVItemTouchHelperMeetActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        rv.apply {
            adapter = StringAdapter()
            layoutManager = GridLayoutManager(this@RVItemTouchHelperMeetActivity, 4)
            ItemTouchHelper(
                MyItemTouchHelperCallBack(
                    (adapter as StringAdapter).LIST_DATA,
                    adapter as StringAdapter
                )
            ).apply {
                attachToRecyclerView(rv)
            }
        }
    }

    inner class MyItemTouchHelperCallBack(
        val mData: MutableList<Int>,
        val mAdapter: StringAdapter
    ) :
        ItemTouchHelper.Callback() {

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return if (recyclerView.layoutManager is GridLayoutManager) {
                val dragFlags =
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
                val swipeFlags = 0
                makeMovementFlags(dragFlags, swipeFlags)
            } else {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN //允许向下滑动
                val swipeFlags = ItemTouchHelper.START //只允许向左滑动
                makeMovementFlags(dragFlags, swipeFlags)
            }
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val dragViewPos = viewHolder.bindingAdapterPosition
            val targetViewPos = target.bindingAdapterPosition
            CmdUtil.v("onMove:$dragViewPos | $targetViewPos")

            Collections.swap(mData, dragViewPos, targetViewPos)
            mAdapter.notifyItemMoved(dragViewPos, targetViewPos)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipePos = viewHolder.bindingAdapterPosition
            CmdUtil.v("onSwiped:$swipePos | $direction")

            mData.removeAt(swipePos)
            mAdapter.notifyItemRemoved(swipePos)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            CmdUtil.v("onSelectedChanged:$actionState")

            val preColor = (viewHolder?.itemView?.background as ColorDrawable?)?.color
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                viewHolder?.itemView?.apply {
                    setBackgroundColor(Color.RED)
                }
            } else if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                viewHolder?.itemView?.apply {
                    setBackgroundColor(Color.YELLOW)
                }
            }
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.apply {
                setBackgroundColor(recyclerView.context.resources.getColor(android.R.color.holo_green_light))
            }
        }
    }
}