package com.example.android_study.android.drawable_and_graph.bitmap.demo.common

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.util.LruCache
import com.bumptech.glide.disklrucache.DiskLruCache
import java.io.File

/**
 * Author:create by RhythmCoder
 * Date:2021/6/1
 * Description:
 */
class ImageLoader {
    private val mImageResizer = ImageResizer()
    private lateinit var mContext: Context

    //cache
    private val MAX_SIZE = Runtime.getRuntime().maxMemory() / 10
    private val mLruCache = object : LruCache<String, Bitmap>(MAX_SIZE.toInt()) {
        override fun sizeOf(key: String?, value: Bitmap?): Int {
            return value?.byteCount ?: 0
        }
    }

    //diskCache
    private lateinit var mCacheDir: File
    private val DISK_CACHE_SIZE = 1024 * 1024 * 5
    private var mDiskLruCache: DiskLruCache? = null

    private constructor(context: Context) {
        mContext = context.applicationContext
        mCacheDir = getDiskCacheDir(mContext, "my_image_loader_cache")
        if (!mCacheDir.exists()) {
            mCacheDir.mkdirs()
        }
        //todo:当mCacheDir不满足DISK_CACHE_SIZE时需要进一步处理
        mDiskLruCache = DiskLruCache.open(mCacheDir, 1, 1,DISK_CACHE_SIZE.toLong())
    }

    private fun getDiskCacheDir(context: Context, name: String): File {
        val state = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        val cacheFile = if (state) {
            context.externalCacheDir?.path ?: ""
        } else {
            context.cacheDir.path
        }

        return File(cacheFile + File.separator + name)
    }

    /**
     * LOAD
     */
    private fun loadBitmapFromHTTP(url: String, width: Int, height: Int) {

    }

    private fun loadBitmapFromDISK(url: String, width: Int, height: Int) {

    }

    private fun loadBitmapFromMERMORY(url: String, width: Int, height: Int) {

    }

    companion object {
        fun create(context: Context): ImageLoader {
            return ImageLoader(context)
        }
    }
}