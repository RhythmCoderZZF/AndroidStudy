package com.example.android_study.android.media.recorder

import android.Manifest
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_media_recorder.*
import java.io.IOException

class MediaRecorderActivity : BaseActivity() {
    private val fileName =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath + "/record.mp3"
    private var recorder: MediaRecorder? = null
    override fun getLayoutId() = R.layout.activity_android_media_recorder

    override fun initViewAndData(savedInstanceState: Bundle?) {

        val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
                permissions.entries.forEach {
                    if (it.value) {
                        CmdUtil.v("权限申请成功:${it.key}")
                    } else {
                        CmdUtil.e("权限申请失败:${it.key}")
                    }
                }
            }

        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
        start.setOnClickListener {
            startRecording()
        }

        stop.setOnClickListener {
            stopRecording()
        }
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
    }
}