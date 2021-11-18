package com.example.android_study.ui.materialDesign.bug

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study.databinding.ItemImageBinding
import kotlinx.android.synthetic.main.activity_coordinator_bug_layout.*

class CoordinatorLayoutBugActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_coordinator_bug_layout

    override fun initViewAndData(savedInstanceState: Bundle?) {
        viewPager.adapter = ImageAdapter().apply {
            submitList(
                    listOf(
                            "http://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/image/2020-12-24/d7547cb9a59c47a2875817f90b974117.jpg",
                            "https://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/images/2020-12-23/71218D44-61CB-486B-BA62-1463363BBBD71608715100.jpg",
                            "https://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/images/2020-12-23/9376E78D-736F-44FC-B025-B0963865ED611608711214.jpg",
                            "http://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/image/2020-12-23/176183ff3dc44a1b907c13517259cacc.jpg",
                            "https://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/images/2020-12-23/B6145D43-7822-4885-BA4E-CEB88B214CAC1608709555.jpg",
                            "http://headquarters-pro.oss-cn-hangzhou.aliyuncs.com/image/2020-12-23/f138988f591845c0aecb68fa0c88fa0f.jpg"
                    )
            )
        }
    }

    class ImageAdapter : BaseDataAdapter<String, ItemImageBinding>(CALLBACK) {
        companion object {
            val CALLBACK = object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

                override fun areContentsTheSame(oldItem: String, newItem: String) =
                        oldItem == newItem
            }
        }

        override fun getLayoutId() = R.layout.item_image

        override fun setVariable(
                data: String,
                position: Int,
                holder: BaseViewHolder<ItemImageBinding>
        ) {
            Glide.with(holder.binding.image.context).load(data).into(holder.binding.image)
        }
    }
}