package com.example.android_study._jetpack._aac_demo.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/17
 * Description:
 */
object Api {
    private const val BASE_URL = "https://www.wanandroid.com/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
    inline fun <reified T> create(): T = create(T::class.java)

//KotlinExtensions中定义了await()扩展函数
//    suspend fun <T> Call<T>.await(): T = suspendCoroutine { continuation ->
//        //异步
//        enqueue(object : Callback<T> {
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                continuation.resumeWith(Result.failure(t))
//            }
//
//            override fun onResponse(call: Call<T>, response: Response<T>) {
//                val body = response.body()
//                if (body != null)
//                    continuation.resumeWith(Result.success(body))
//                else {
//                    continuation.resumeWith(Result.failure(Exception("body is null")))
//                }
//            }
//        })
//    }
}