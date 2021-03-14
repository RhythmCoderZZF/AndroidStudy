package com.example.android_study.ui.recyclerView.diffutil.payload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.databinding.ItemBaseBinding
import kotlinx.android.synthetic.main.fragment_u_i_recycler_view_diff_util_payload.*
import kotlinx.coroutines.delay

/**
 * Author:create by RhythmCoder
 * Date:2021/3/13
 * Description:
 */
class UIRVDiffUtilPayloadFragment : BaseFragment() {
    private val mAdapter = MyAdapter()
    private val oldList = listOf(
            E(1, 100), E(2, 56), E(3, 88)
    )
    private val newList = listOf(
            E(1, 0), E(2, 56), E(3, 88)
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_recycler_view_diff_util_payload, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv.apply {
            adapter = mAdapter.apply {
                submitList(oldList)
            }
        }

        lifecycleScope.launchWhenCreated {
            delay(2000)
            mAdapter.submitList(newList)
        }
    }

    private data class E(
            val id: Int,
            val data: Int
    )

    private class MyAdapter : ListAdapter<E, BaseViewHolder<ItemBaseBinding>>(D) {

        companion object {
            private val openPayload = false
            private val D = object : DiffUtil.ItemCallback<E>() {
                override fun areItemsTheSame(oldItem: E, newItem: E) = oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: E, newItem: E) = oldItem == newItem

                override fun getChangePayload(oldItem: E, newItem: E): Any? {
                    val bundle = Bundle()
                    if (openPayload) {
                        if (oldItem.data != newItem.data) {
                            return bundle.apply {
                                putInt("i", newItem.data)
                            }
                        }
                    }
                    return bundle
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemBaseBinding> =
                BaseViewHolder(ItemBaseBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: BaseViewHolder<ItemBaseBinding>, position: Int, payloads: MutableList<Any>) {
            CmdUtil.v("onBindViewHolder<3> payloads:${payloads}")
            CmdUtil.i("holder:${holder.hashCode()}")
            if (payloads.isNotEmpty()) {
                holder.binding.apply {
                    tv.text = "${getItem(position).data}"
                }
                return
            }
            super.onBindViewHolder(holder, position, payloads)
        }

        override fun onBindViewHolder(holder: BaseViewHolder<ItemBaseBinding>, position: Int) {
            CmdUtil.v("onBindViewHolder<2>")
            CmdUtil.i("holder:${holder.hashCode()}")
            holder.binding.apply {
                tv.text = "${getItem(position).data}"
            }
        }
    }
}