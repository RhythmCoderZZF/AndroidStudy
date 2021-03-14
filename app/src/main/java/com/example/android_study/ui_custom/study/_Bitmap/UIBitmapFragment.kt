package com.example.android_study.ui_custom.study._Bitmap

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.dp2px
import kotlinx.android.synthetic.main.fragment_u_i_cus_bitmap.*
import kotlin.math.abs

class UIBitmapFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_u_i_cus_bitmap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //extractAlpha
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.tiktok_play_tiktok)
        val bitmapRes = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmapRes)
        canvas.drawBitmap(bitmap.extractAlpha(), 0f, 0f, Paint().apply {
            color = Color.BLUE
        })
        iv.setImageBitmap(bitmapRes)

        //extractAlpha(Paint,Offset)
        val blurRadius = 30f.dp2px()
        val offset = IntArray(2)
        val bitmapRes1 = Bitmap.createBitmap(bitmap.width + blurRadius * 2, bitmap.height + blurRadius * 2, Bitmap.Config.ARGB_8888)
        Canvas(bitmapRes1).apply {
            drawBitmap(bitmap.extractAlpha(Paint().apply {
                maskFilter = BlurMaskFilter(blurRadius.toFloat(), BlurMaskFilter.Blur.SOLID)
            }, offset), offset[0].toFloat() + blurRadius, offset[1].toFloat() + blurRadius, Paint().apply {
                color = Color.RED
            })

            drawRect(0f, 0f, bitmap.width + blurRadius.toFloat() * 2, bitmap.height + blurRadius.toFloat() * 2, Paint().apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 2f
            })
        }
        CmdUtil.v("模糊半径:$blurRadius;偏移:${offset[0]};${offset[1]}")
        iv1.setImageBitmap(bitmapRes1)

        //density
        val birmap = BitmapFactory.decodeResource(resources, R.mipmap.tiktok_play_tiktok)
        CmdUtil.v("0图片density:${birmap.density}|宽高:${birmap.width}|${birmap.height} 内存:${birmap.allocationByteCount}")

        val srcBitmap = BitmapFactory.decodeResource(resources, R.mipmap.tiktok_play_tiktok, BitmapFactory.Options().apply {
            inDensity = resources.displayMetrics.densityDpi / 2
        })

        CmdUtil.v("1图片density:${srcBitmap.density}|宽高:${srcBitmap.width}|${srcBitmap.height}  内存:${srcBitmap.allocationByteCount}")
        iv4.setImageBitmap(srcBitmap)

        val srcBitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.tiktok_play_tiktok).apply {
            density = resources.displayMetrics.densityDpi / 2
        }

        CmdUtil.v("2图片density:${srcBitmap1.density}|宽高:${srcBitmap1.width}|${srcBitmap1.height}  内存:${srcBitmap1.allocationByteCount}")
        iv5.setImageBitmap(srcBitmap1)

    }

}