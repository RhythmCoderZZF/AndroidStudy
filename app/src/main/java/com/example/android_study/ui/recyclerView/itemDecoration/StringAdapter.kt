package com.example.android_study.ui.recyclerView.itemDecoration

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study.databinding.ItemJetpackPagingBinding

/**
 * Author:create by RhythmCoder
 * Date:2021/5/4
 * Description:
 */
class StringAdapter : BaseDataAdapter<Int, ItemJetpackPagingBinding>(CALL_BACK) {
    companion object {
        private val CALL_BACK = object : DiffUtil.ItemCallback<Int>() {
            override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
        }
        val mList= mutableListOf<Int>().apply {
            repeat(100){
                add(it)
            }
        }
    }

    override fun getLayoutId() = R.layout.item_jetpack_paging

    override fun setVariable(data: Int, position: Int, holder: BaseViewHolder<ItemJetpackPagingBinding>) {
        holder.binding.apply {
            tv.text = data.toString()
        }
    }


}