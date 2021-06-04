package com.example.android_study.android.drawable_and_graph.bitmap.lrucache_disk

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.disklrucache.DiskLruCache
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_android_bitmap_lrucache_disk.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class AndroidDiskLruCacheActivity : BaseActivity() {
    private val datas = arrayOf("0", "1", "2", "3", "4", "5", "6")
    private val picKey =
        hashKeyForDisk("https://cdn.pixabay.com/photo/2021/03/01/09/29/woman-6059236_960_720.jpg")

    override fun getLayoutId() = R.layout.activity_android_bitmap_lrucache_disk
    override fun initViewAndData(savedInstanceState: Bundle?) {
        window.decorView.post(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun run() {
        CmdUtil.showWindow()

        val myCacheFile = File("${dataDir.absolutePath + "/myDiskCacheDir"}")
        //1.
        if (!myCacheFile.exists()) {
            myCacheFile.mkdirs()
        }
        //2.
        var cache = DiskLruCache.open(myCacheFile, 1, 1, 4)
        btn_write.setOnClickListener {
            datas.forEachIndexed { i, d ->
                val editor = cache.edit("$i")
                editor.set(0, "$d")
                editor.commit()
                CmdUtil.v("put:$d")
            }
            cache.flush()
            cache.close()
        }

        cache = DiskLruCache.open(myCacheFile, 1, 1, 4)
        btn_read.setOnClickListener {
            launch {
                datas.forEachIndexed { i, d ->
                    val value = cache.get("$i")
                    value?.let {
                        val res = it.getString(0)
                        CmdUtil.i("$res")
                    }
                }
                cache.flush()
                cache.close()
            }
        }

        val myPicCacheDir = File("${dataDir.absolutePath + "/myPicCacheDir"}")
        if (!myPicCacheDir.exists()) {
            myPicCacheDir.mkdirs()
        }
        var picCache = DiskLruCache.open(myPicCacheDir, 1, 1, 1024 * 1024)
        btn_write_pic.setOnClickListener {
            launch {
                withContext(IO) {
                    val edit =
                        picCache.edit(picKey)

                    val file = edit.getFile(0);
                    val bos = BufferedOutputStream(file.outputStream())
                    val url =
                        URL("https://cdn.pixabay.com/photo/2021/03/01/09/29/woman-6059236_960_720.jpg")
                    val connection = (url.openConnection() as HttpURLConnection).apply {
                        connectTimeout = 2000
                        connect()
                    }
                    val io = connection.inputStream
                    val bis = BufferedInputStream(io)
                    var len: Int
                    val byteArray = ByteArray(1024)
                    try {
                        while (bis.read(byteArray).also { len = it } != -1) {
                            bos.write(byteArray, 0, len)
                        }
                    } catch (e: Exception) {
                    } finally {
                        bis.close()
                        bos.close()
                    }
                    edit.commit()
                    picCache.flush()
                }
                CmdUtil.v("success")
            }
        }

        btn_read_pic.setOnClickListener {
            var picCache = DiskLruCache.open(myPicCacheDir, 1, 1, 1024 * 1024)
            val v = picCache.get(picKey)
            val bis: BufferedInputStream = if (v == null) {
                val edit = picCache.edit(picKey)
                BufferedInputStream(edit.getFile(0).inputStream())
            } else {
                BufferedInputStream(picCache.get(picKey).getFile(0).inputStream())
            }
            val bitmap = BitmapFactory.decodeStream(bis)
            pic.setImageBitmap(bitmap)
        }
    }

    fun hashKeyForDisk(key: String): String? {
        return try {
            val mDigest: MessageDigest = MessageDigest.getInstance("MD5")
            mDigest.update(key.toByteArray())
            bytesToHexString(mDigest.digest())
        } catch (e: NoSuchAlgorithmException) {
            key.hashCode().toString()
        }
    }

    private fun bytesToHexString(bytes: ByteArray): String? {
        val sb = StringBuilder()
        for (i in bytes.indices) {
            val hex = Integer.toHexString(0xFF and bytes[i].toInt())
            if (hex.length == 1) {
                sb.append('0')
            }
            sb.append(hex)
        }
        return sb.toString()
    }
}