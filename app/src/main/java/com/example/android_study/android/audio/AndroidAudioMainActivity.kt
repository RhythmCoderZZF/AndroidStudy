package com.example.android_study.android.audio

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidAudioMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.API", AudioApiActivity::class.java, "相关API"),
        ))

    }
}