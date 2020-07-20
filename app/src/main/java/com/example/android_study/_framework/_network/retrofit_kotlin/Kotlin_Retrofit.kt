package com.example.android_study._framework._network.retrofit_kotlin

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/15
 * Description:
 */
suspend fun <T> Call<T>.await() = suspendCoroutine<T> { continuation ->
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            continuation.resumeWithException(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            if (body != null) continuation.resumeWith(Result.success(body)) else continuation.resumeWithException(RuntimeException("body is null"))
        }

    })
}