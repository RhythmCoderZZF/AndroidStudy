package com.example.android_study.android.media.custom_mediaplayer

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_study._base.App
import com.example.android_study._base.cmd.CmdUtil
import java.io.File

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

    fun setFileAndPlay(file: File) {
        mediaPlayer.reset()
        mediaPlayer.apply {
            setDataSource(
                App.instance,
                Uri.fromFile(file)
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
            setOnErrorListener { mp, what, extra ->
                CmdUtil.e("err what:$what;extra:$extra")
                false
            }
            setOnInfoListener { mp, what, extra ->
                CmdUtil.v("info what:$what;extra:$extra")
                false
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