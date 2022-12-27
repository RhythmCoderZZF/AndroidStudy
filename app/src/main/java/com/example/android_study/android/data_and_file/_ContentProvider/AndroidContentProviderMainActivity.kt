package com.example.android_study.android.data_and_file._ContentProvider

import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import java.io.*

class AndroidContentProviderMainActivity : BaseActivity() {
    private val TAG = "zzf"

    override fun getLayoutId() = R.layout.fragment_d_f_content_provider

    override fun initViewAndData(savedInstanceState: Bundle?) {

        /* test */
        val file =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/QALog/micro_count")
        writeValue(file.absolutePath, "1")
    }


    @Synchronized
    private fun writeValue(filePath: String, msg: String) {
        val start = System.currentTimeMillis()
        val fos: FileOutputStream
        var bw: BufferedWriter? = null
        try {
            fos = FileOutputStream(filePath)
            bw = BufferedWriter(OutputStreamWriter(fos))
            bw.write(msg)
            bw.flush()
            Log.d(TAG, "write value $filePath:$msg")
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            Log.d(TAG, "writeValue() cost:" + (System.currentTimeMillis() - start) + "ms")
            try {
                bw?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}