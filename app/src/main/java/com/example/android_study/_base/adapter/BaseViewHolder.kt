package com.example.android_study._base.adapter

import android.media.MediaPlayer
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author kuky.
 * @description RecyclerView Adapter View Holder 基类
 */
open class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)