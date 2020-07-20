package com.example.android_study._framework._network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/13
 * Description:
 */
object Api {
    private const val BASE_URL = "https://www.wanandroid.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}