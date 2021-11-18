package com.example.android_study.android.drawable_and_graph.bitmap.loadBitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_bitmap.*

class AndroidBitmapActivity : BaseActivity(), Runnable {


    override fun getLayoutId() = R.layout.activity_android_bitmap
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)


    }

    override fun run() {
        //1.直接加载大图
        val bytes = assets.open("bigPic.png").readBytes()
        CmdUtil.v("资源bytes:${bytes.size / 1024 / 1024} M")
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        val size = bitmap.allocationByteCount / 1024 / 1024f
        iv.setImageBitmap(bitmap)
        CmdUtil.v("原图 宽:${bitmap.width} 高:${bitmap.height} size:$size M")


        val bitmap1 = decodeBitmapFromBitmap(bytes, iv1.width, iv1.height)
        val size1 = bitmap1.allocationByteCount / 1024 / 1024f
        CmdUtil.i("压缩后 宽:${bitmap1.width} 高:${bitmap1.height} size:$size1 M")
        iv1.setImageBitmap(bitmap1)
    }

    /**
     * reqWidth/reqHeight:ImageView的宽高
     */
    private fun decodeBitmapFromBitmap(bytes: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true//只计算宽高，不生成bitmap

            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, this)//只计算宽高，不生成bitmap

            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)//根据bitmap宽高，iv的宽高计算缩放倍数

            inJustDecodeBounds = false//生成bitmap

            BitmapFactory.decodeByteArray(bytes, 0, bytes.size, this)
        }
    }


    /**
     * 计算要缩放的inSampleSize大小：
     * 1. 长和宽各缩放的大小（2的倍数）
     */
    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
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