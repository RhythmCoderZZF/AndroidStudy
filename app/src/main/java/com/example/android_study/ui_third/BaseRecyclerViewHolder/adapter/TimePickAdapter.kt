package com.example.android_study.ui_third.BaseRecyclerViewHolder.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.android_study.R

/**
 * Author:create by RhythmCoder
 * Date:2020/10/14
 * Description:
 */
class TimePickAdapter : BaseQuickAdapter<Int, TimePickHolder>(R.layout.item_recycler_view_gesture) {
    override fun convert(holder: TimePickHolder, item: Int) {
        holder.setText(R.id.tv, "$item")
    }
}

class TimePickHolder(itemView: View) : BaseViewHolder(itemView)
