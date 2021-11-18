package com.example.android_study.android.activity.result_api

import android.content.Intent
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_result_api_send.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/23
 * Description:
 */
class ActivityResultApiSendAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_result_api_send

    override fun initViewAndData(savedInstanceState: Bundle?) {
        submit.setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra("data", edt.text.toString())
            })
            finish()
        }
    }
}