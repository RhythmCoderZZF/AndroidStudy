package com.example.android_study.android.drawable_and_graph.bitmap.demo.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.FileDescriptor
import java.io.InputStream

/**
 * Author:create by RhythmCoder
 * Date:2021/6/1
 * Description:
 */
class ImageResizer {
    fun decodeBitmapFromBitmap(bytes: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true//只计算宽高，不生成bitmap
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, this)
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)//根据bitmap宽高，iv的宽高计算缩放倍数
            inJustDecodeBounds = false//生成bitmap
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, this)
        }
    }

    fun decodeSampleBitmapFromFileDescriptor(
        fd: FileDescriptor,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true//只计算宽高，不生成bitmap
            BitmapFactory.decodeFileDescriptor(fd, null, this)
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)//根据bitmap宽高，iv的宽高计算缩放倍数
            inJustDecodeBounds = false//生成bitmap
            BitmapFactory.decodeFileDescriptor(fd, null, this)
        }
    }

    fun decodeSampleBitmapFromStream(bytes: InputStream, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true//只计算宽高，不生成bitmap
            BitmapFactory.decodeStream(bytes, null, this)
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)//根据bitmap宽高，iv的宽高计算缩放倍数
            inJustDecodeBounds = false//生成bitmap
            BitmapFactory.decodeStream(bytes, null, this)
        }
    }

    /**
     * 计算要缩放的inSampleSize大小：
     * 1. 长和宽各缩放的大小（2的倍数）
     */
    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        val (w, h) = options.run { outWidth to outHeight }//Pair(w;h)
        var inSampleSize = 1
        if (w > reqWidth || h > reqHeight) {
            val halfW = w / 2
            val halfH = h / 2
            while (halfW / inSampleSize > reqWidth || halfH / inSampleSize > reqHeight) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}