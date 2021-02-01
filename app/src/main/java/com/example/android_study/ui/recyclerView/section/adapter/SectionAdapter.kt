package com.example.android_study.ui.recyclerView.section.adapter

import android.app.LauncherActivity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.BaseExpandableListAdapter
import androidx.core.view.marginStart
import androidx.recyclerview.widget.DiffUtil
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study.databinding.ItemBaseBinding
import com.example.android_study.ui.recyclerView.section.Bean
import kotlin.math.exp

/**
 * Author:create by RhythmCoder
 * Date:2020/12/28
 * Description:
 */
class SectionAdapter : BaseDataAdapter<Bean, ItemBaseBinding>(DIFF_CALLBACK) {
    var expendListener: ((Bean.Footer) -> Unit)? = null

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Bean>() {
            override fun areItemsTheSame(oldItem: Bean, newItem: Bean) = if (oldItem is Bean.Header && newItem is Bean.Header) {
                oldItem.string == newItem.string
            } else if(oldItem is Bean.Content && newItem is Bean.Content){
                oldItem.string == newItem.string
            }else{
                false
            }

            override fun areContentsTheSame(oldItem: Bean, newItem: Bean) = oldItem == newItem
        }
    }

    override fun getLayoutId() = R.layout.item_base
    override fun setVariable(data: Bean, position: Int, holder: BaseViewHolder<ItemBaseBinding>) {
        when (getItemViewType(position)) {
            1 -> {
                holder.binding.tv.text = (data as Bean.Header).string
                holder.binding.tv.setTextColor(Color.RED)
                showLayout(holder.binding.root,true)
            }
            2 -> {
                val d = data as Bean.Content
                holder.binding.tv.text = d.string
                holder.binding.tv.setTextColor(Color.GREEN)
                if (d.show) {
                    showLayout(holder.binding.root,true)
                }else
                    showLayout(holder.binding.root,false)
            }
            3 -> {
                val d = data as Bean.Footer
                if (d.show) {
                    showLayout(holder.binding.root, true)
                } else
                    showLayout(holder.binding.root, false)
                holder.binding.tv.text = "展开"
                holder.binding.tv.setOnClickListener {
                    expendListener?.invoke(data)
                }
            }
        }


    }

    private fun showLayout(view: View, show: Boolean) {
        if (show) {
            (view.layoutParams as ViewGroup.MarginLayoutParams).apply {
                width = ViewGroup.MarginLayoutParams.MATCH_PARENT
                height = ViewGroup.MarginLayoutParams.WRAP_CONTENT
                setMargins(0, 10, 0, 10)
            }
            view.visibility = View.VISIBLE
        } else {
            (view.layoutParams as ViewGroup.MarginLayoutParams).apply {
                width = 0
                height = 0
                setMargins(0, 0, 0, 0)
            }
            view.visibility = View.GONE
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Bean.Header -> {
                1
            }
            is Bean.Content -> {
                2
            }
            is Bean.Footer -> {
                3
            }
            else -> -1
        }

    }
}

