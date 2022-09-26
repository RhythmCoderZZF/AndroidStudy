package com.example.android_study.android.media

import android.content.Context
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_audio_api.*

class AudioApiActivity : BaseActivity() {
    private var mAudioRequest: AudioFocusRequest? = null
    private lateinit var mAudioManager: AudioManager

    /**
     * 音频焦点变化后回调，LOSS指的是被其他APP抢占丢失焦点
     */
    private val mAudioFocusChangeListener: AudioManager.OnAudioFocusChangeListener =
        AudioManager.OnAudioFocusChangeListener {
            when (it) {
                AudioManager.AUDIOFOCUS_GAIN ->
                    CmdUtil.v("AUDIOFOCUS_GAIN")
                AudioManager.AUDIOFOCUS_LOSS ->
                    CmdUtil.v("AUDIOFOCUS_LOSS")
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ->
                    CmdUtil.v("AUDIOFOCUS_LOSS_TRANSIENT")
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                    CmdUtil.v("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK")

            }
        }

    override fun getLayoutId() = R.layout.activity_android_audio_api

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        mAudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        btn_req_1.setOnClickListener {
            abandonAudioFocusRequest()
            val request = mAudioManager.requestAudioFocus(
                AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)//永久抢占
                    .setOnAudioFocusChangeListener(mAudioFocusChangeListener).build().also {
                        mAudioRequest = it
                    })
            CmdUtil.v("$request")
        }
        btn_req_2.setOnClickListener {
            abandonAudioFocusRequest()
            val request = mAudioManager.requestAudioFocus(
                AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK)//短暂抢占
                    .setOnAudioFocusChangeListener(mAudioFocusChangeListener).build().also {
                        mAudioRequest = it
                    })
            CmdUtil.v("$request")
        }
    }

    private fun abandonAudioFocusRequest() {
        mAudioRequest?.let {
            mAudioManager.abandonAudioFocusRequest(it)
            mAudioRequest = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        abandonAudioFocusRequest()
    }
}