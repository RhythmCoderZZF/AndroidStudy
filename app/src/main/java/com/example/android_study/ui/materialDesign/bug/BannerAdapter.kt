package com.example.android_study.ui.materialDesign.bug

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android_study.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * Author:create by nbhysj
 * Date:2020/11/13
 * Description:
 */
class BannerAdapter : BaseBannerAdapter<String, BannerViewHolder>() {
    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View?,
        viewType: Int
    ): BannerViewHolder {
        return BannerViewHolder(itemView as ImageView)
    }

    override fun onBind(holder: BannerViewHolder?, data: String?, position: Int, pageSize: Int) {
        holder?.bindData(data, position, pageSize)
    }

    override fun getLayoutId(viewType: Int) = R.layout.item_banner
}

class BannerViewHolder(val view: ImageView) : BaseViewHolder<String>(view) {
    override fun bindData(data: String?, position: Int, pageSize: Int) {
        data?.let {
            Glide.with(view.context).load(data).into(view)
        }
    }
}