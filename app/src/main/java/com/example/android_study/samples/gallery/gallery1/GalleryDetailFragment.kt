package com.example.android_study.samples.gallery.gallery1

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.android_study.R
import com.example.android_study.samples.gallery.gallery1.adapter.GalleryListAdapter.Companion.PHOTO
import com.example.android_study.samples.gallery.model.Hit
import kotlinx.android.synthetic.main.fragment_gallery_detail.*

class GalleryDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val parcelable = arguments?.getParcelable<Hit>(PHOTO) ?: return
        shimmer.showShimmer(true)

        Glide.with(requireContext())
                .load(parcelable.largeImageURL)
                .placeholder(R.mipmap.icon_placeholder_image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false.apply { shimmer?.hideShimmer() }
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        return false.apply { shimmer?.hideShimmer() }
                    }
                })
                .into(photoView)

    }
}