package com.example.android_study.android.drawable_and_graph.bitmap

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.drawable_and_graph.bitmap.demo.AndroidLoadBitmapDemoActivity
import com.example.android_study.android.drawable_and_graph.bitmap.loadBitmap.AndroidBitmapActivity
import com.example.android_study.android.drawable_and_graph.bitmap.lrucache.AndroidLruCacheActivity
import com.example.android_study.android.drawable_and_graph.bitmap.lrucache_disk.AndroidDiskLruCacheActivity
import kotlinx.android.synthetic.main.activity_rv.*

class AndroidBitmapMainActivity : BaseActivity() {


    override fun getLayoutId() = R.layout.activity_rv
    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
            Entry("1.正确加载Bitmap", AndroidBitmapActivity::class.java,"利用采样压缩防止加载大图"),
            Entry("2.LruCache", AndroidLruCacheActivity::class.java,"内存缓存"),
            Entry("3.DiskLruCache", AndroidDiskLruCacheActivity::class.java,"磁盘缓存"),
            Entry("4.完整图片下载三级缓存案例", AndroidLoadBitmapDemoActivity::class.java,),
        ))
    }
}