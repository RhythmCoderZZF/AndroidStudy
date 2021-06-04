package com.example.android_study.network.okhttp.use.fragments

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_network_okhttp_use.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.addHeaderLenient
import okio.BufferedSink
import org.android.agoo.common.CallBack
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.math.sin

/**
 * Author:create by RhythmCoder
 * Date:2021/6/2
 * Description:
 */
class OkHttpUseFragment : BaseFragment() {
    //1.构建唯一实例OkHttpClient
    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .build()

    private val picUrl = "https://cdn.pixabay.com/photo/2021/05/11/05/57/men-6245003_960_720.jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CmdUtil.showWindow()
        return inflater.inflate(R.layout.fragment_network_okhttp_use, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_get.setOnClickListener {
            val request = Request.Builder().url("https://www.baidu.com").build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val code = response.code
                    val msg = response.message
                    CmdUtil.v(response.toString())
                    CmdUtil.v("——\n")
                    response.headers.forEach {
                        CmdUtil.v(it.toString())
                    }
                    CmdUtil.v("请求头——\n")
                    CmdUtil.i(response.body?.string())
                }
            })

        }

        btn_get_1.setOnClickListener {
            val request = Request.Builder()
                .addHeader("haha", "h1")
                .addHeader("haha", "h2")
                .header("hei", "H1")
                .header("hei", "H2")
                .url("https://cdn.pixabay.com/photo/2021/05/11/05/57/men-6245003_960_720.jpg")
                .build()
            val call = client.newCall(request)
            thread {
                val response = call.execute()
                if (response.code == 200) {
                    val `is` = response.body?.byteStream()
                    val bitmap = BitmapFactory.decodeStream(`is`)
                    (requireContext() as Activity).runOnUiThread {
                        get_pic.setImageBitmap(bitmap)
                    }
                }
            }
        }

        btn_post.setOnClickListener {
            val requestBody =
                """{"content": "🚀","phone": "13000000000","username": "2"}""".trimIndent()
                    .toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("http://murloc.nbhysj.com/api/customer/save")
                .addHeader(
                    "Authorization",
                    "9!s8AYd7l6l26lLe7SjVOgMmQcKo3psyh0aj70pFx7X4CbJwPhheHR0xOiaUEDSaGOHA3cYCoMFNGDudgo4waNnh0otFNFX17bE0XV"
                )
                .addHeader("os", "Android")
                .post(requestBody)
                .build()

            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 200) {
                        val res = response.body.toString()
                        CmdUtil.v(res)
                        CmdUtil.i("请求:" + response.request.toString())
                    }
                }
            })
        }

        btn_post_image.setOnClickListener {
            if (get_pic.drawable == null) {
                Toast.makeText(requireContext(), "请先下载图片", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val bitmap = (get_pic.drawable as BitmapDrawable).bitmap

            val imageBody = object : RequestBody() {
                override fun contentType() = "image/jpeg".toMediaType()

                override fun writeTo(sink: BufferedSink) {
                    val os = sink.outputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, os)
                }
            }
            val requestBody = MultipartBody.Builder()
                .addFormDataPart("file", "TestPic.jpg", imageBody)
                .setType(MultipartBody.FORM)
                .build()
            val request = Request.Builder()
                .url("http://murloc.nbhysj.com/api/user/updateAvater")
                .addHeader(
                    "Authorization",
                    "9!s8AYd7l6l26lLe7SjVOgMmQcKo3psyh0aj70pFx7X4CbJwPhheHR0xOiaUEDSaGOHA3cYCoMFNGDudgo4waNnh0otFNFX17bE0XV"
                )
                .addHeader("os", "Android")
                .post(requestBody)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 200) {
                        val res = response.body.toString()
                        CmdUtil.v(res)
                        CmdUtil.i("请求:" + response.request.toString())
                    }
                }
            })
        }

        btn_post_image_file.setOnClickListener {
            if (get_pic.drawable == null) {
                Toast.makeText(requireContext(), "请先下载图片", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val bitmap = (get_pic.drawable as BitmapDrawable).bitmap

            val picPath = requireContext().externalCacheDir?.path + File.separator + "haha.jpg"
            val picFile = File(picPath)
            if (!picFile.exists()) {
                picFile.createNewFile()
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, picFile.outputStream())

            val imageBody = picFile.asRequestBody("image/jpeg".toMediaType())

            val requestBody = MultipartBody.Builder()
                .addFormDataPart("file", "TestPicFile.jpg", imageBody)
                .setType(MultipartBody.FORM)
                .build()
            val request = Request.Builder()
                .url("http://murloc.nbhysj.com/api/user/updateAvater")
                .addHeader(
                    "Authorization",
                    "9!s8AYd7l6l26lLe7SjVOgMmQcKo3psyh0aj70pFx7X4CbJwPhheHR0xOiaUEDSaGOHA3cYCoMFNGDudgo4waNnh0otFNFX17bE0XV"
                )
                .addHeader("os", "Android")
                .post(requestBody)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code == 200) {
                        val res = response.body.toString()
                        CmdUtil.v(res)
                        CmdUtil.i("请求:" + response.request.toString())
                    }
                }
            })
        }
    }
}