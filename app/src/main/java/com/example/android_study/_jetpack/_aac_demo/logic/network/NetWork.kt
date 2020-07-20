package com.example.android_study._jetpack._aac_demo.logic.network

import retrofit2.await

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/17
 * Description:
 */
object NetWork {
    private val apiService = Api.create<ApiService>()

    suspend fun getBanner() = apiService.getBanner().await()
}