package com.example.android_study.android.media

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.SurfaceHolder
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android_study.R
import com.example.android_study._base.App
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.media.custom_mediaplayer.MyMediaPlayer
import kotlinx.android.synthetic.main.activity_media_player_custom.*
import kotlinx.android.synthetic.main.activity_media_player_simple.*
import java.io.File


class MediaPlayerMusicActivity : BaseActivity() {
    private val mediaPlayer = MyMediaPlayer()
    override fun getLayoutId() = R.layout.activity_simple_tv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions: Map<String, Boolean> ->
                permissions.entries.forEach {
                    if (it.value) {
                        CmdUtil.v("权限申请成功:${it.key}")
                        init()
                    } else {
                        CmdUtil.e("权限申请失败:${it.key}")
                    }
                }
            }
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
        )
    }

    private fun init() {
        val dir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)// storage/emulated/0/Download
        val file = File(dir, "music.mp3")
        if (!file.exists()) {
            return
        }
        CmdUtil.v("file:${file.absolutePath}")
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
            prepareAsync()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

}