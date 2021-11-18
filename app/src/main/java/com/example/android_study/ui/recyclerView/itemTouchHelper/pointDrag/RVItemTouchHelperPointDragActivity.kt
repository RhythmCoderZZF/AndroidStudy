package com.example.android_study.ui.recyclerView.itemTouchHelper.pointDrag

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_rv.*
import java.util.*

class RVItemTouchHelperPointDragActivity : BaseActivity() {

    private lateinit var mItemTouchHelper: ItemTouchHelper

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        rv.apply {
            adapter = PointDragAdapter { holer ->
                mItemTouchHelper.startDrag(holer)
            }

            mItemTouchHelper = ItemTouchHelper(
                MyItemTouchHelperCallBack(
                    (adapter as PointDragAdapter).LIST_DATA,
                    adapter as PointDragAdapter
                )
            ).apply {
                attachToRecyclerView(rv)
            }
        }
    }

    inner class MyItemTouchHelperCallBack(
        val mData: MutableList<Int>,
        val mAdapter: PointDragAdapter
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

        override fun isLongPressDragEnabled() = false
        override fun isItemViewSwipeEnabled() = false
    }
}