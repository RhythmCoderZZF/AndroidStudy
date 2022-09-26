package com.example.android_study.android.media.simple

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_media_player_simple.*


class MediaPlayerSimpleActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_media_player_simple

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val uri = "android.resource://" + packageName + "/" + R.raw.main
        videoView.setVideoPath(uri)
        videoView.setOnPreparedListener {
            it.isLooping=true
        }

        videoView.start()
    }
}