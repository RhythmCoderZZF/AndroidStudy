package com.example.android_study.android.activity.result_api

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_result_api.*
import java.security.Permission

/**
 * Author:create by RhythmCoder
 * Date:2021/7/23
 * Description:
 */
class ActivityResultApiReceiveAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_result_api

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val intent = it.data
                intent?.let {
                    val data = it.getStringExtra("data")
                    tv.text = data
                }
            }
        }
        tv.setOnClickListener {
            launcher.launch(Intent(this, ActivityResultApiSendAy::class.java))
        }


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
        btn_request_permission.setOnClickListener {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
    }
}