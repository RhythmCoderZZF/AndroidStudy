package com.example.android_study.ui.recyclerView.diffutil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.util.LogUtil
import com.example.android_study.ui.recyclerView.gesture.adapter.GestureRvAdapter
import kotlinx.android.synthetic.main.activity_u_i_recycler_view_diff_util.*
import kotlinx.coroutines.delay

class UIRecyclerViewDiffUtilActivity : BaseActivity() {
    private val oldList = mutableListOf("1","2","3","4","5","6","7","8")
//    private val newList = mutableListOf("1","2","3","4","5","6","7","8","9","10")
    private val newList = mutableListOf("1","3","2","9","6","7","8","10")
    private val myDiffCallBack = object : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
    }

    private val mAdapter = GestureRvAdapter(oldList)
    private val mAdapter2 by lazy { Rv2Adapter() }

    override fun getLayoutId() = R.layout.activity_u_i_recycler_view_diff_util

    override fun initViewAndData(savedInstanceState: Bundle?) {
        rv1.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@UIRecyclerViewDiffUtilActivity)

            lifecycleScope.launchWhenCreated {
                delay(2000)
                mAdapter.setList(newList)
                val diffResult = DiffUtil.calculateDiff(myDiffCallBack, true)
                diffResult.dispatchUpdatesTo(mAdapter)
            }
        }


        rv2.apply {
            adapter = mAdapter2
            layoutManager = LinearLayoutManager(this@UIRecyclerViewDiffUtilActivity)
            mAdapter2.submitList(oldList)

            lifecycleScope.launchWhenCreated {
                delay(2000)
                mAdapter2.submitList(newList)
            }
        }

    }

    private val myDiffItemCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }

    private inner class Rv2Adapter : ListAdapter<String, Rv2Adapter.Rv2ViewHolder>(myDiffItemCallBack) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rv2ViewHolder {
            return Rv2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_gesture, parent, false))
        }

        override fun onBindViewHolder(holder: Rv2ViewHolder, position: Int) {
            LogUtil.d("zzz","$position")
            holder.bind(getItem(position))
        }

        private inner class Rv2ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            fun bind(str: String) {
                itemView.findViewById<TextView>(R.id.tv).text = str
            }
        }
    }
}
