package com.example.android_study.android.drawable_and_graph.bitmap.lrucache

import android.os.Bundle
import androidx.collection.LruCache
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AndroidLruCacheActivity : BaseActivity() {
    private val MAX_SIZE = 5
    private val mCache = object : LruCache<Int, Int>(MAX_SIZE) {
        override fun sizeOf(key: Int, value: Int): Int {
            return 1
        }
    }

    override fun getLayoutId() = R.layout.activity_android_bitmap_lrucache
    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    override fun run() {
        CmdUtil.showWindow()
        launch {
            repeat(7) {
                mCache.put(it, it)
                CmdUtil.v("put:$it|$it")
                delay(100)
            }
            CmdUtil.i("${mCache.snapshot()}")
        }

    }
}