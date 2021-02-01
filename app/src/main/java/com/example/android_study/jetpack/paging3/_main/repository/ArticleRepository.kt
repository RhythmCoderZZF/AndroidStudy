package com.example.android_study.jetpack.paging3._main.repository

import com.example.android_study._base.net.net.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Author:create by RhythmCoder
 * Date:2020/10/9
 * Description:
 */
class ArticleRepository {
    suspend fun loadArticles(page: Int) = withContext(Dispatchers.IO) {
        delay(2000)
        ApiService.create().getArticle(page).data.datas
    }
}