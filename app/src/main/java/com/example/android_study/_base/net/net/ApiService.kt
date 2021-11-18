package com.example.android_study._base.net.net

import com.example.android_study._base.net.models.Banner
import com.example.android_study._base.net.models.BaseResponse
import com.example.android_study._base.net.models.Page
import com.example.android_study._base.net.models.data.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 */

interface ApiService {
    @GET("banner/json")
    suspend fun getBanner(): BaseResponse<MutableList<Banner>>

    @GET("banner/json")
    fun getBanner1(): Call<BaseResponse<MutableList<Banner>>>

    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") page: Int): BaseResponse<Page<Article>>

    companion object {
        private const val BASE_URL = "https://www.wanandroid.com/"
        fun create(): ApiService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(ApiService::class.java)
        }
    }
}
