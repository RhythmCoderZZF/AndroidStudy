package com.example.android_study.ui.recyclerView.itemTouchHelper.pointDrag

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.recyclerview.widget.DiffUtil
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study.databinding.ItemTouchHelperPointDragBinding

/**
 * Author:create by RhythmCoder
 * Date:2021/5/4
 * Description:
 */
class PointDragAdapter(var dragListener: ((BaseViewHolder<ItemTouchHelperPointDragBinding>) -> Unit)? = null) :
    BaseDataAdapter<Int, ItemTouchHelperPointDragBinding>(CALL_BACK) {
    val LIST_DATA = mutableListOf<Int>().apply {
        repeat(100) {
            add(it)
        }
    }
    init {
        submitList(LIST_DATA)
    }
    companion object {
        private val CALL_BACK = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
        }
    }

    override fun getLayoutId() = R.layout.item_touch_helper_point_drag

    @SuppressLint("ClickableViewAccessibility")
    override fun setVariable(
        data: Int,
        position: Int,
        holder: BaseViewHolder<ItemTouchHelperPointDragBinding>
    ) {
        holder.binding.apply {
            tv.text = data.toString()
            picDarg.setOnTouchListener { v, event ->


                if (event.action == MotionEvent.ACTION_UP) {
                    v.performClick()
                } else if (event.action == MotionEvent.ACTION_DOWN) {
                    dragListener?.invoke(holder)
                }
                false
            }
        }
    }
}