package com.example.android_study.base.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/10
 * Description:
 */
class RvAdapter(var list: List<Entry>) : RecyclerView.Adapter<RvAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = list[position].title
        holder.itemView.setOnClickListener { _ ->
            val intent = Intent(holder.textView.context, list[position].clazz)
            intent.putExtra("title", list[position].title)
            holder.textView.context.startActivity(intent)
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tv)

    }

}