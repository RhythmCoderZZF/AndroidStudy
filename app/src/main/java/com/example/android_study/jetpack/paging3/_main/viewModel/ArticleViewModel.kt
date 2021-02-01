package com.example.android_study.jetpack.paging3._main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.android_study.jetpack.paging3._main.repository.ArticlePagingSource
import com.example.android_study.jetpack.paging3._main.repository.ArticleRepository

/**
 * Author:create by RhythmCoder
 * Date:2020/10/9
 * Description:
 */
class ArticleViewModel : ViewModel() {
    private lateinit var pagingSource: ArticlePagingSource

    //Flow<PagingData<Value>>
    fun getHomeArticles() = Pager(PagingConfig(20)) {
        ArticlePagingSource(ArticleRepository()).apply {
            pagingSource=this
        }
    }.flow.cachedIn(viewModelScope)

    fun deleteFirst() {
        pagingSource.list.removeAt(0)
    }

    fun changeFirst() {
        pagingSource.list[0].title="dsfasdfsadfasf"
    }
}