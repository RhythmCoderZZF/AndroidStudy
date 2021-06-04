package com.example.android_study.framework.glide

import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_frame_work_glide.*

class FWGlideAy : BaseActivity() {
    private val pics = arrayOf(
        "https://cdn.pixabay.com/photo/2017/12/11/15/34/lion-3012515_960_720.jpg"
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_frame_work_glide
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        Glide.with(this)
            .load(pics[0])
//            .placeholder(R.drawable.bg_clear_day)
            .transition(withCrossFade())
            .thumbnail(Glide.with(this).load(R.mipmap.ic_launcher))
            .into(imageView)
    }
}