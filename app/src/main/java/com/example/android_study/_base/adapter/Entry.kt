package com.example.android_study._base.adapter

import androidx.appcompat.app.AppCompatActivity
import com.example.android_study._base.BaseFragment

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/29
 * Description:
 */
data class Entry(
        val title: String,
        val clazz: Class<out AppCompatActivity>,
        val description: String? = null
)

data class EntryF(
        val title: String,
        val clazz: BaseFragment,
)