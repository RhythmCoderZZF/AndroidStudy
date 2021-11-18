package com.example.android_study._base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author zzf.
 * @description Paging 下的点击事件
 */
fun interface OnItemClickListener<T> {
    fun onItemClick(position: Int, view: View?, data: T)
}
fun interface OnItemClickListener2<T> {
    fun onItemClick(position: Int, viewHolder: RecyclerView.ViewHolder?, data: T)
}

fun interface OnItemLongClickListener<T> {
    fun onItemLongClick(position: Int, view: View?, data: T)
}