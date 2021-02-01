package com.example.android_study._base.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Author:create by RhythmCoder
 * Date:2020/12/28
 * Description:
 */
val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}