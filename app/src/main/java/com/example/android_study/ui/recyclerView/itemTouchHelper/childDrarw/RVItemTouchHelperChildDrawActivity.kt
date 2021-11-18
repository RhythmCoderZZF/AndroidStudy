package com.example.android_study.ui.recyclerView.itemTouchHelper.childDrarw

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.StringAdapter
import kotlinx.android.synthetic.main.activity_rv.*
import java.util.*

class RVItemTouchHelperChildDrawActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        rv.apply {
            adapter = StringAdapter()
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

        private val mPaint=Paint().apply {
            color = Color.BLUE
            style=Paint.Style.FILL
        }
        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            CmdUtil.i("onChildDraw pos:${viewHolder.bindingAdapterPosition}|dx:$dX|dy:$dY|active:$isCurrentlyActive")
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        }

        override fun onChildDrawOver(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder?,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDrawOver(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
            c.drawRect(100f, 0f, 200f, 20f,mPaint)
        }
    }
}