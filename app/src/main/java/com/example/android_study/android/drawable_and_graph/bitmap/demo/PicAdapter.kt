package com.example.android_study.android.drawable_and_graph.bitmap.demo

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import androidx.recyclerview.widget.DiffUtil
import com.example.android_study.R
import com.example.android_study._base.adapter.BaseDataAdapter
import com.example.android_study._base.adapter.BaseViewHolder
import com.example.android_study.android.drawable_and_graph.bitmap.demo.common.ImageLoaderCopy
import com.example.android_study.databinding.ItemAndroidBitmapCacheBinding
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

/**
 * Author:create by RhythmCoder
 * Date:2021/6/1
 * Description:
 */
class PicAdapter(val context: Context) :
    BaseDataAdapter<String, ItemAndroidBitmapCacheBinding>(CALLBACK) {
    private val executors = Executors.newFixedThreadPool(5)

    private val mImageLoader = ImageLoaderCopy(context)

    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

    override fun getLayoutId() = R.layout.item_android_bitmap_cache
    override fun setVariable(
        data: String,
        position: Int,
        holder: BaseViewHolder<ItemAndroidBitmapCacheBinding>
    ) {
        holder.binding.root.post {
            mImageLoader.bindBitmap(
                data,
                holder.binding.image,
                holder.binding.image.width,
                holder.binding.image.height
            )
        }

    }

    private fun fetchPic(picUrl: String, bos: BufferedOutputStream) {
        val url = URL(picUrl)
        val connection = (url.openConnection() as HttpURLConnection).apply {
            connectTimeout = 2000
            connect()
        }
        val io = connection.inputStream
        val bis = BufferedInputStream(io)
        var len: Int
        val byteArray = ByteArray(1024 * 3)
        try {
            while (bis.read(byteArray).also { len = it } != -1) {
                bos.write(byteArray, 0, len)
            }
        } catch (e: Exception) {
        } finally {
            bis.close()
            bos.close()
        }
    }
}