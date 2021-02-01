package com.example.android_study.jetpack.demo_wanandroid.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 */
@Entity
data class History(
        @PrimaryKey(autoGenerate = true)
        val id: Int=0,
        val content: String
)