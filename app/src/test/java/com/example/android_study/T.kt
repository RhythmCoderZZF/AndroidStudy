package com.example.android_study

import com.example.android_study.jetpack.demo_wanandroid.net.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2020/8/20
 * Description:
 */
class T {
    @Test
    fun t() {
        runBlocking { ApiService.create().getArticle(1) }
    }
}