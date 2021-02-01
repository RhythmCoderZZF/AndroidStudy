package com.example.android_study.jetpack.paging3._main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study._base.net.models.data.Article
import com.example.android_study.databinding.ItemJetpackPagingBinding
import kotlinx.coroutines.launch

/**
 * Author:create by RhythmCoder
 * Date:2020/10/10
 * Description:
 */
class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.BindingViewHolder>(DIFF_CALLBACK) {
    lateinit var mPagingData: PagingData<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemJetpackPagingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val binding = holder.binding
        binding.tv.text = getItem(position)?.title
    }

    suspend fun appendItem(item: Article) {
        if (!this::mPagingData.isInitialized) {
            throw IllegalArgumentException("To add data, you must use the 'setPagingData' method")
        }
        mPagingData = mPagingData.insertFooterItem(item)
        submitPagingData()
    }

    private suspend fun submitPagingData() {
        submitData(mPagingData)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                    oldItem == newItem
        }
    }

    class BindingViewHolder(val binding: ItemJetpackPagingBinding) : RecyclerView.ViewHolder(binding.root)
}
