package com.example.android_study.samples.gallery.gallery2.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android_study.R
import com.example.android_study.other.utils.ui.ScreenUtil
import com.example.android_study._base.util.LogUtil
import com.example.android_study.samples.gallery.model.Hit
import kotlinx.android.synthetic.main.list_gallery.view.*

/**
 * Author:create by RhythmCoder
 * Date:2020/8/21
 * Description:
 */


class GalleryList2Adapter : ListAdapter<Hit, GalleryList2Adapter.VH>(DIFF_CALLBACK) {


    object DIFF_CALLBACK : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Hit, newItem: Hit) = oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gallery, parent, false)
        val holder = VH(view)
        holder.itemView.setOnClickListener {
            Bundle().also {
                it.putParcelableArrayList(PHOTO_LIST, ArrayList(currentList))
                it.putInt(POSITION, holder.bindingAdapterPosition)
                holder.itemView.findNavController().navigate(R.id.action_galleryListFragment_to_galleryViewPagerFragment, it)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val shimmer = holder.itemView.shimmer
        shimmer.showShimmer(true)

        val layoutParams = holder.itemView.imageView.layoutParams
        val itemWidth = (ScreenUtil.getScreenWidth(shimmer.context) - ScreenUtil.dp2px(shimmer.context, 12f)) / 2
        layoutParams.width = itemWidth
        val scale = (itemWidth + 0f) / getItem(position).webformatWidth
        layoutParams.height = (getItem(position).webformatHeight * scale).toInt()
        holder.itemView.imageView.layoutParams = layoutParams


        Glide.with(holder.itemView)
                .load(getItem(position).webformatURL)
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
    companion object {
        const val PHOTO_LIST = "photo"
        const val POSITION = "position"
    }
}