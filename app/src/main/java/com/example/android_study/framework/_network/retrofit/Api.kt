package com.example.android_study.framework._network.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/13
 * Description:
 */
object Api {
    private const val BASE_URL = "https://www.wanandroid.com/"
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}