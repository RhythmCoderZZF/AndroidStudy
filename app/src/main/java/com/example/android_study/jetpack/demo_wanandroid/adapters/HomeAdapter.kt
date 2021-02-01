package com.example.android_study.jetpack.demo_wanandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study.jetpack.demo_wanandroid.models.data.Article
import kotlinx.android.synthetic.main.item_wanandrid.view.*

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 * DiffUtil:
 * 1. DiffUtil封装一个List<T>
 * 2. 新的list会和原来的list比较，只会notify有差异的item
 * 3. 比较操作在后台计算，不会阻塞主线程
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    //1. 定义diff_callback指定item差异的算法
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    //2. 定义differ，传入定义diff_callback指定item差异的算法
    private val mDiffer = AsyncListDiffer(this, DIFF_CALLBACK)

    var itemClickListener: ((Article) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wanandrid, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = mDiffer.currentList.size


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = mDiffer.currentList[position]
        holder.itemView.apply {
            title.text = article.title
            content.text = article.desc
            author.text = article.author
            date.text = article.niceDate
            setOnClickListener {
                itemClickListener?.also { it(article) }
            }
        }
    }
    //3. List<T>传入differ
    fun submitList(list: List<Article>) {
        mDiffer.submitList(list)
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        itemClickListener = listener
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}