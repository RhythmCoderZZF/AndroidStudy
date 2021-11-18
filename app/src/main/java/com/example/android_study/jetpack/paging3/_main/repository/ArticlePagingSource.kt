package com.example.android_study.jetpack.paging3._main.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android_study._base.net.models.data.Article

/**
 * Author:create by RhythmCoder
 * Date:2020/10/9
 * Description:
 */
class ArticlePagingSource(private val repository: ArticleRepository) : PagingSource<Int, Article>() {
    val list= mutableListOf<Article>()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> =
            try {
                var page = params.key ?: 0
                if (page == 0) {
                    list.clear()
                }
                val article = repository.loadArticles(page)
                list.addAll(article)

                LoadResult.Page(
                        data = article,
                        prevKey = if (page == 0) null else page - 1,
                        nextKey = if (article.isEmpty()) null else page + 1)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }

}