package com.example.android_study.samples.gallery.net

import com.example.android_study.samples.gallery.model.Gallery
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 */

interface PixabayService {


    @GET("/api")
    suspend fun getGallery(@Query("key") key: String = "17987047-e00474ee4ec96ae315b21dc30", @Query("q") search: String = keyWords.random()): Gallery

    companion object {
        private val keyWords = arrayOf("super car", "sex girl", "model", "lol", "universe", "sun")
        private const val BASE_URL = "https://pixabay.com/"
        fun create(): PixabayService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build().create(PixabayService::class.java)
        }
    }
}
