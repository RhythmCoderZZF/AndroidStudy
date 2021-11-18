package com.example.android_study.android.mediaPlayer.custom_mediaplayer

import android.media.MediaPlayer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Author:create by RhythmCoder
 * Date:2021/6/8
 * Description:
 */
class MyMediaPlayer : MediaPlayer(), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumePlay() {
        start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pausePlay() {
        pause()
    }
}