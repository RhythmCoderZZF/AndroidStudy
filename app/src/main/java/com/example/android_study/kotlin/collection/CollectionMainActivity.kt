package com.example.android_study.kotlin.collection

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.collection.stream_sequence.KotlinCollectionStreamSequenceActivity
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class CollectionMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_main

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("Stream and Sequence", KotlinCollectionStreamSequenceActivity::class.java)
        ))
    }
}