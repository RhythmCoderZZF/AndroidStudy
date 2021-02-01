package com.example.android_study.android.data_and_file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.drawable_and_graph.bitmap.AndroidBitmapActivity
import com.example.android_study.android.drawable_and_graph.drawable.AndroidDrawableActivity
import com.example.android_study.android.drawable_and_graph.transtionDrawable.AndroidTransitionDrawableActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidDataAndFileMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_android_data_and_file_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.drawable", AndroidDrawableActivity::class.java),
        ))

    }
}