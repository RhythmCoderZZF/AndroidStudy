package com.example.android_study._jetpack._aac_demo.logic.network

import com.example.android_study._jetpack._aac_demo.logic.model.BackResult
import com.example.android_study._jetpack._aac_demo.logic.model.bean.Banner
import retrofit2.Call
import retrofit2.http.GET

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/17
 * Description:
 */
interface ApiService {
    @GET("https://www.wanandroid.com/banner/json")
    fun getBanner(): Call<BackResult<List<Banner>>>

}