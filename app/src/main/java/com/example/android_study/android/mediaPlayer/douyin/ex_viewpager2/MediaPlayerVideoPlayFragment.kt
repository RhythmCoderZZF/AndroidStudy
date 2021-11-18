package com.example.android_study.android.mediaPlayer.douyin.ex_viewpager2

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_media_player_video_play.*
import kotlinx.coroutines.delay


class MediaPlayerVideoPlayFragment(private val position: Int, private val url: String) : BaseFragment(

) {
    override val TAG: String
        get() = "$position:"
    private var mediaPlayer: MediaPlayer? = null

    init {
        showLifecycle = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mediaPlayer = MediaPlayer()
        return inflater.inflate(R.layout.fragment_media_player_video_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaPlayer?.apply {
            setOnPreparedListener {
                loading.visibility = View.INVISIBLE
                seekTo(1)
                progressBar.max = mediaPlayer?.duration ?: 0
                CmdUtil.v("${mediaPlayer?.duration ?: 0}")
            }
            setDataSource(url)
            prepareAsync()
            loading.visibility = View.VISIBLE
        }
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                mediaPlayer?.setDisplay(holder)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }
        })


        lifecycleScope.launchWhenResumed {
            while (true) {
                progressBar.progress = mediaPlayer?.currentPosition ?: 0
                CmdUtil.v("${mediaPlayer?.currentPosition ?: 0}")
                delay(500)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            while (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
                delay(500)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}