package com.example.android_study.jetpack.camera

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.jetpack.camera.preview.CameraXPreviewActivity
import com.example.android_study.jetpack.camera.recordVideo.CameraXRecordVideoActivity
import kotlinx.android.synthetic.main.activity_android_camera_main.*

class CameraXMainActivity : BaseActivity() {
    override fun getLayoutId()=R.layout.activity_android_camera_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.预览", CameraXPreviewActivity::class.java),
                Entry("2.视频录制", CameraXRecordVideoActivity::class.java)
        ))
    }
}