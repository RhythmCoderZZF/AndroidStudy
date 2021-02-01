package com.example.android_study.android.drawable_and_graph

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.drawable_and_graph.bitmap.AndroidBitmapActivity
import com.example.android_study.android.drawable_and_graph.drawable.AndroidDrawableActivity
import com.example.android_study.android.drawable_and_graph.transtionDrawable.AndroidTransitionDrawableActivity
import kotlinx.android.synthetic.main.activity_fragment_main.*

class AndroidDrawableGraphMainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_android_drawable_and_graph_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1.drawable", AndroidDrawableActivity::class.java),
                Entry("2.bitmap", AndroidBitmapActivity::class.java),
                Entry("2.TransitionDrawable", AndroidTransitionDrawableActivity::class.java),
        ))

    }
}