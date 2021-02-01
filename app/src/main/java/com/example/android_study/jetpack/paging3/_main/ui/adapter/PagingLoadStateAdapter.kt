package com.example.android_study.jetpack.paging3._main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.util.LogUtil
import com.example.android_study.databinding.RecyclerLoadFooterBinding

/**
 * @author kuky.
 * @description
 */
class PagingLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PagingLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
            : PagingLoadStateViewHolder {
        return PagingLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

class PagingLoadStateViewHolder(
        private val binding: RecyclerLoadFooterBinding,
        retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryLoadData.setOnClickListener { retry()}
    }

    fun bind(loadState: LoadState) {
        LogUtil.d("zzf","$loadState")
        binding.loadMore.isVisible = loadState is LoadState.Loading
        binding.retryLoadData.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_load_footer, parent, false
            )
            return PagingLoadStateViewHolder(
                RecyclerLoadFooterBinding.bind(view), retry
            )
        }
    }
}