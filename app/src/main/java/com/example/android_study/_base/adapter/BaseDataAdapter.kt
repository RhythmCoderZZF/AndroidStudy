package com.example.android_study._base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * @author kuky.
 * @description paging adapter 基类, 如果需要多布局, 请使用 MergeAdapter
 */
abstract class BaseDataAdapter<T : Any, VB : ViewDataBinding>(val callback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseViewHolder<VB>>(callback) {

    var itemListener: OnItemClickListener<T>? = null
    var itemLongClickListener: OnItemLongClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutId(),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        val data = getItem(position) ?: return
        setVariable(data, position, holder)
        holder.binding.executePendingBindings()
        holder.binding.root.run {
            setOnClickListener { itemListener?.onItemClick(position, it, data) }
            setOnLongClickListener {
                itemLongClickListener?.onItemLongClick(position, it,data)
                false
            }
        }
    }


    abstract fun getLayoutId(): Int

    abstract fun setVariable(data: T, position: Int, holder: BaseViewHolder<VB>)
}