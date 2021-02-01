package com.example.android_study._base.adapter

import android.view.View

/**
 * @author zzf.
 * @description Paging 下的点击事件
 */
fun interface OnItemClickListener<T> {
    fun onItemClick(position: Int, view: View?, data: T)
}


fun interface OnItemLongClickListener<T> {
    fun onItemLongClick(position: Int, view: View?, data: T)
}