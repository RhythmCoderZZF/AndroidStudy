package com.example.android_study.samples.gallery

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.samples.gallery.gallery1.GalleryActivity
import com.example.android_study.samples.gallery.gallery2.Gallery2Activity
import kotlinx.android.synthetic.main.activity_gallery_main.*

class GalleryMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_gallery_main
    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1. Gallery", GalleryActivity::class.java, "简易画廊"),
                Entry("2. Gallery2", Gallery2Activity::class.java, "简易画廊(ViewPager2升级版)")
        ))
    }
}