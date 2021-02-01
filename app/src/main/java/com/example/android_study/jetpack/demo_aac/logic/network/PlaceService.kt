package com.example.android_study.jetpack.demo_aac.logic.network
import com.example.android_study.jetpack.demo_aac.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${ServiceCreator.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}