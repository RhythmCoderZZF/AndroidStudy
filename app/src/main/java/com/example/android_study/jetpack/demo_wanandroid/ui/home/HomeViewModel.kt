package com.example.android_study.jetpack.demo_wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_study.jetpack.demo_wanandroid.models.BaseResponse
import com.example.android_study.jetpack.demo_wanandroid.models.Page
import com.example.android_study.jetpack.demo_wanandroid.models.data.Article
import com.example.android_study.jetpack.demo_wanandroid.net.Resource
import com.example.android_study.jetpack.demo_wanandroid.net.handleResult
import kotlinx.coroutines.launch

class HomeViewModel(val repository: HomeRepository) : ViewModel() {
    //1. 被观察的网络源数据 com.example.android_study._base.net.net.Resource<BaseResponse<Page<Article>>>
    val liveArticles: MutableLiveData<Resource<BaseResponse<Page<Article>>>> = MutableLiveData()

    init {
        getArticles(1)
    }
    //2. 调用repository获取接口数据，并处理结果状态
    fun getArticles(page: Int) = viewModelScope.launch {
        liveArticles.postValue(Resource.Loading())
        val response = repository.getArticles(page)
        liveArticles.postValue(handleResult(response))
    }


}