package com.example.android_study.jetpack.demo_wanandroid.ui.home

import com.example.android_study.jetpack.demo_wanandroid.net.ApiService

class HomeRepository {
    suspend fun getArticles(page: Int) = ApiService.create().getArticle(page)
}
