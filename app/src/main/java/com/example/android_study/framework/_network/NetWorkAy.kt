package com.example.android_study.framework._network

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.framework._network.retrofit.DataManager
import com.example.android_study.framework._network.retrofit.HomeData
import com.example.android_study.framework._network.retrofit_kotlin.await
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
        CmdUtil.showWindow()
    }

    /**
     * HttpUrlConnection
     */
    fun httpUrlConnection(view: View) = Thread {
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
    }.start()
//
    /**
     * OkHttp
     */
    fun okhttp(view: View) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url("https://www.baidu.com").build()
        val execute = okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            //okhttp onResponse回调结果默认是在子线程
            override fun onResponse(call: Call, response: Response) {
                CmdUtil.v("当前线程: ${Thread.currentThread().name}")
                val string = response.body?.string()
                runOnUiThread {
                    showToast(string)
                }
            }
        })

    }

    /**
     * retrofit
     */
    fun retrofit(view: View) {
        DataManager.getJson().enqueue(object : retrofit2.Callback<com.example.android_study.framework._network.retrofit.BackResult<List<HomeData>>> {
            override fun onFailure(call: retrofit2.Call<com.example.android_study.framework._network.retrofit.BackResult<List<HomeData>>>, t: Throwable) {
                runOnUiThread { showToast(t.printStackTrace().toString()) }
            }
            //retrofit onResponse回调结果默认是在主线程
            override fun onResponse(call: retrofit2.Call<com.example.android_study.framework._network.retrofit.BackResult<List<HomeData>>>, backResult: retrofit2.Response<com.example.android_study.framework._network.retrofit.BackResult<List<HomeData>>>) {
                CmdUtil.v("当前线程: ${Thread.currentThread().name}")
                runOnUiThread { showToast(backResult.body().toString()) }
            }
        })
    }

    /**
     * retrofit with kotlin
     */
    fun retrofitWithKotlin(view: View) {
        lifecycleScope.launch {
            val response = DataManager.getJson().await()
            runOnUiThread {
                showToast(response.toString())
            }
        }
    }
}
