package com.example.android_study._base.net.models


data class BaseResponse<T>(
        val data: T,
        val errorCode: Int,
        val errorMsg: String
)

data class Page<R>(
        val curPage: Int,
        val datas: List<R>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)

