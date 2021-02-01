package com.example.android_study.samples.gallery.gallery2.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android_study.R
import com.example.android_study._base.util.LogUtil
import com.example.android_study.samples.gallery.model.Hit
import kotlinx.android.synthetic.main.list_gallery_view_pager.view.*

/**
 * Author:create by RhythmCoder
 * Date:2020/8/21
 * Description:
 */


class GalleryViewPagerAdapter : ListAdapter<Hit, GalleryViewPagerAdapter.VH>(DIFF_CALLBACK) {


    object DIFF_CALLBACK : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit) = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery_view_pager, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val shimmer = holder.itemView.shimmer
        shimmer.showShimmer(true)
        Glide.with(holder.itemView)
                .load(getItem(position).largeImageURL)
                .placeholder(R.mipmap.icon_placeholder_image)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        LogUtil.d("zzf", "onResourceReady")
                        return false.also { holder.itemView.shimmer?.hideShimmer() }
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        LogUtil.e("zzf", "onLoadFailed")
                        return false.also { holder.itemView.shimmer?.hideShimmer() }
                    }
                })
                .into(holder.itemView.imageView)
    }

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView)
}