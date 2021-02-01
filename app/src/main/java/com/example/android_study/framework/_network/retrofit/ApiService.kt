package com.example.android_study.framework._network.retrofit

import retrofit2.Call
import retrofit2.http.GET

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/13
 * Description:
 */
interface ApiService {
    @GET("friend/json")
    fun getJson(): Call<BackResult<List<HomeData>>>
}