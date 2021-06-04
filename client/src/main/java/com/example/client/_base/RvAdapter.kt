package com.example.client._base

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.client.R
import kotlinx.android.synthetic.main.item_base.view.*

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/10
 * Description:
 */
class RvAdapter(var list: List<Entry>) : RecyclerView.Adapter<RvAdapter.VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_base, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tv.text = list[position].title
        if (TextUtils.isEmpty(list[position].description)) {
            holder.itemView.description.visibility = View.GONE
        } else {
            holder.itemView.description.visibility = View.VISIBLE
            holder.itemView.description.text = list[position].description
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.tv.context, list[position].clazz)
            intent.putExtra("title", list[position].title)
            holder.itemView.tv.context.startActivity(intent)
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}