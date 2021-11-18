package com.example.android_study.samples.largePhoto.third_framework

import android.graphics.PointF
import android.os.Bundle
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_sample_large_photo_third_inflate.*
import java.lang.Exception

class LargePhotoThirdMainActivity : BaseActivity() {

    private val pics = listOf(
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.lnstzy.cn%2Faoaodcom%2F2020-04%2F03%2F2020040306173726bfebf8176acb1f00598f582ac92d67.jpg.w7680.h4320.jpg%3Fdown&refer=http%3A%2F%2Fimage.lnstzy.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633228236&t=5b3b03df5b2420829c9ee577ea5b051b",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Ff00685dcc4aed7b535f95a404b6f755a0c79245c.jpg&refer=http%3A%2F%2Fi0.hdslb.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633229835&t=40dcdced3c1927bf044081b11ac6ef5c",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.vjshi.com%2F2019-01-10%2F293b9b5ac24ea6695048252d4a60ed04%2F00004.jpg%3Fx-oss-process%3Dstyle%2Fwatermark&refer=http%3A%2F%2Fpic.vjshi.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633229835&t=a6937632459e957730128ed3da96ba26",
    )

    private var mCurr = 0
    override fun getLayoutId() = R.layout.activity_sample_large_photo_third_inflate

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    override fun run() {
        myMapView.apply {
            setMapFromNet(pics[0])
            mSSIV.setOnImageEventListener(object : SubsamplingScaleImageView.OnImageEventListener {
                override fun onReady() {
                    CmdUtil.v("onReady")
                    myMapView.apply {
                        addPoints(
                            mutableListOf(
                                PointF(0f, 0f),
                                PointF(200f, 400f),
                                PointF(1000f, 2000f),
                                PointF(2000f, 3000f),
                            )
                        )
                    }
                }

                override fun onImageLoaded() {
                    CmdUtil.v("onImageLoaded")
                }

                override fun onPreviewLoadError(e: Exception?) {
                    CmdUtil.v("onPreviewLoadError")
                }

                override fun onImageLoadError(e: Exception?) {
                    CmdUtil.v("onImageLoadError")
                }

                override fun onTileLoadError(e: Exception?) {
                    CmdUtil.v("onTileLoadError")
                }

                override fun onPreviewReleased() {
                    CmdUtil.v("onPreviewReleased")
                }
            })
        }
        btnChangeMap.setOnClickListener {
            mCurr++
            if (mCurr > 2) {
                mCurr = 0
            }
            myMapView.setMapFromNet(pics[mCurr])
        }
    }

}