package com.example.android_study.android.mediaPlayer.custom_mediaplayer

import android.content.res.Resources
import android.os.Bundle
import android.view.Gravity
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.viewModels
import com.example.android_study.R
import com.example.android_study._base.App
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study._base.util.immersion
import com.example.android_study._base.util.screenWidth
import kotlinx.android.synthetic.main.activity_media_player_custom.*
import kotlinx.android.synthetic.main.activity_media_player_simple.*
import okhttp3.internal.wait


class MediaPlayerCustomActivity : BaseActivity() {
    private val mViewModel by viewModels<MainViewModel>()
    override fun getLayoutId() = R.layout.activity_media_player_custom

    override fun initViewAndData(savedInstanceState: Bundle?) {
        immersion()
        lifecycle.addObserver(mViewModel.mediaPlayer)
        mViewModel.setUrlAndPlay("android.resource://" + App.instance.packageName + "/" + R.raw.main)

        mViewModel.videoSize.observe(this) {
            surface_view.post {
                resize(it.first, it.second)
            }
        }
        var b=false
        btnChange.setOnClickListener {
            b=!b
            if (b) {
                mViewModel.setUrlAndPlay("android.resource://" + App.instance.packageName + "/" + R.raw.local_video)
            } else {
                mViewModel.setUrlAndPlay("android.resource://" + App.instance.packageName + "/" + R.raw.main)
            }

        }
        surface_view.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                CmdUtil.v("surfaceChanged:$width - $height")
                mViewModel.mediaPlayer.setDisplay(holder)
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })
    }

        /**
         * 根据视频宽高
         */
        private fun resize(width: Int, height: Int) {
            if (width == 0 || height == 0) return
            val ratio = width.toFloat() / height
            val containerH=(surface_view.parent as ViewGroup).height
            val surfaceW = (containerH * ratio).toInt()
            val scale= Resources.getSystem().displayMetrics.widthPixels.toFloat() / surfaceW

            surface_view.layoutParams =
                FrameLayout.LayoutParams(surfaceW, ViewGroup.LayoutParams.MATCH_PARENT,Gravity.CENTER)
            surface_view.scaleX=scale
            surface_view.scaleY=scale
        }
}