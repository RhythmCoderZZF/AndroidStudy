package com.example.android_study._framework._network

import android.os.Bundle
import android.view.View
import com.example.android_study.R
import com.example.android_study._framework._network.retrofit.Data
import com.example.android_study._framework._network.retrofit.DataManager
import com.example.android_study._framework._network.retrofit.Result
import com.example.android_study._framework._network.retrofit_kotlin.await
import com.example.android_study.base.BaseActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetWorkAy : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_net_work_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
    }

    /**
     * HttpUrlConnection
     */
    fun httpUrlConnection(view: View) = Thread(Runnable {
        val sb = StringBuilder()
        val url = URL("https://www.wanandroid.com")
        var httpURLConnection: HttpURLConnection? = null
        try {
            httpURLConnection = (url.openConnection() as HttpURLConnection).apply {
                connectTimeout = 8000
                readTimeout = 8000
                connect()
            }
            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            bufferedReader.use {
                bufferedReader.forEachLine {
                    sb.append(it)
                }
            }
        } catch (e: Exception) {
            runOnUiThread { showToast(e.printStackTrace().toString()) }
        } finally {
            httpURLConnection?.disconnect()
        }
        runOnUiThread {
            showToast(sb.toString())
        }
    }
    ).start()

    /**
     * OkHttp
     */
    fun okhttp(view: View) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url("https://www.baidu.com").build()
        val execute = okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            //回调结果默认是在子线程
            override fun onResponse(call: Call, response: Response) {
                val string = response.body()?.string()
                runOnUiThread { showToast(string) }
            }
        })

    }

    /**
     * retrofit
     */
    fun retrofit(view: View) {
        DataManager.getJson().enqueue(object : retrofit2.Callback<Result<List<Data>>> {
            override fun onFailure(call: retrofit2.Call<Result<List<Data>>>, t: Throwable) {
                runOnUiThread { showToast(t.printStackTrace().toString()) }
            }

            override fun onResponse(call: retrofit2.Call<Result<List<Data>>>, result: retrofit2.Response<Result<List<Data>>>) {
                runOnUiThread { showToast(result.body().toString()) }
            }
        })
    }
    /**
     * retrofit with kotlin
     */
    fun retrofit1(view: View) {
        var response: Any
        GlobalScope.launch {
            response = DataManager.getJson().await()
            runOnUiThread {
                showToast(response.toString())
            }
        }
    }
}
