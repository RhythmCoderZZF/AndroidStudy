package com.example.android_study.android.mediaPlayer.custom_mediaplayer

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_study.R
import com.example.android_study._base.App
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author:create by RhythmCoder
 * Date:2021/6/8
 * Description:
 */
class MainViewModel : ViewModel() {
    val mediaPlayer = MyMediaPlayer()
    val videoSize = MutableLiveData<Pair<Int, Int>>()



    fun setUrlAndPlay(url: String) {
        mediaPlayer.reset()
        mediaPlayer.apply {
            setDataSource(
                App.instance,
                Uri.parse(url)
            )
        }
        startVideo()
    }

    private fun startVideo() {
        mediaPlayer.apply {
            setOnPreparedListener {
                it.isLooping = true
                it.start()
            }

            setOnVideoSizeChangedListener { mp, width, height ->
                videoSize.postValue(Pair(width, height))
                CmdUtil.v("OnVideoSizeChanged:$width|$height")
            }

            prepareAsync()
        }
    }

    override fun onCleared() {
        super.onCleared()
        CmdUtil.e("onCleared")
        mediaPlayer.release()
    }
}