package com.example.client._base

import androidx.appcompat.app.AppCompatActivity

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
