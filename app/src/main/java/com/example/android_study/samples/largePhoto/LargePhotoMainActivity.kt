package com.example.android_study.samples.largePhoto

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.samples.largePhoto.region_inflate.LargePhotoRegionActivity
import com.example.android_study.samples.largePhoto.third_framework.LargePhotoThirdMainActivity
import kotlinx.android.synthetic.main.activity_rv.*

class LargePhotoMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
            Entry("1.区域加载大图",LargePhotoRegionActivity::class.java,"利用BitmapRegionDecoder实现根据手势动态加载区域图片"),
            Entry("2.第三方框架实现",LargePhotoThirdMainActivity::class.java,"subsampling-scale-image-view第三方框架实现"),
        ))
    }
}