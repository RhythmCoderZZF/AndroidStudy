package com.example.android_study.kotlin.flow.networkSample

import androidx.lifecycle.ViewModel
import com.example.android_study.jetpack.demo_wanandroid.models.BaseResponse
import com.example.android_study.jetpack.demo_wanandroid.models.Page
import com.example.android_study.jetpack.demo_wanandroid.models.data.Article
import com.example.android_study.jetpack.demo_wanandroid.net.ApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * Author:create by RhythmCoder
 * Date:2020/9/23
 * Description:
 */
class SampleViewModel : ViewModel() {

    private var wanHomePageFlow: Flow<BaseResponse<Page<Article>>>? = null

    suspend fun getWanHomePage(page: Int) = wanHomePageFlow ?: flow {
        emit(
                withContext(IO) {
                    ApiService.create().getArticle(page)
                }
        )
    }.apply { wanHomePageFlow = this }
}
