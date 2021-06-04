package com.example.android_study.android.data_and_file.internal_and_outspace

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.android_study.R
import com.example.android_study._base.App
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_android_data_and_file_out_space.*
import java.io.*

/**
 * Author:create by RhythmCoder
 * Date:2021/3/9
 * Description:
 */
class OutFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_android_data_and_file_out_space, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _1 = requireContext().externalMediaDirs// storage/emulated/0/Android/media/com.example.android_study
        val _2 = requireContext().externalCacheDir// storage/emulated/0/Android/data/com.example.android_study/cache
        val _3 = requireContext().externalCacheDirs
        val _4 = requireContext().getExternalFilesDirs(Environment.MEDIA_SHARED)// storage/emulated/0/Android/data/com.example.android_study/files/shared
        val _5 = requireContext().getExternalFilesDirs("")// storage/emulated/0/Android/data/com.example.android_study/files
        val _6 = requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)// storage/emulated/0/Android/data/com.example.android_study/files/DCIM
        val _7 = requireContext().getExternalFilesDir("")// storage/emulated/0/Android/data/com.example.android_study/files

        val _8 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)// storage/emulated/0/DCIM
        val _9 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)// storage/emulated/0/Download
        val _10 = Environment.getExternalStorageDirectory()// storage/emulated/0


        _1.forEach {
            CmdUtil.e(it.absolutePath)
        }
        CmdUtil.e(_2?.absolutePath)
        _3.forEach {
            CmdUtil.e(it.absolutePath)
        }
        _4.forEach {
            CmdUtil.e(it.absolutePath)
        }
        _5.forEach {
            CmdUtil.e(it.absolutePath)
        }
        CmdUtil.e(_6?.absolutePath)
        CmdUtil.e(_7?.absolutePath)
        CmdUtil.e(_8?.absolutePath)
        CmdUtil.e(_9?.absolutePath)
        CmdUtil.e(_10?.absolutePath)

        /* 1. 写externalCache */
        val file = File(requireContext().externalCacheDir, "myCache.txt")
        btnWrite.setOnClickListener {
            r(file)
        }

        btnRead.setOnClickListener {
            w(file)
        }


        val file1 = File.createTempFile("myTemp", ".txt", requireContext().externalCacheDir)
        btnWrite1.setOnClickListener {
            r(file1)
        }

        btnRead1.setOnClickListener {
            w(file1)
        }
    }

    //检查外部存储是否可读写（外部存储会添加移除）
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    private fun w(file1: File?) {
        if (file1 == null || !file1.exists()) {
            Toast.makeText(requireContext(), "文件不存在", Toast.LENGTH_SHORT).show()
            return
        }
        val br = BufferedReader(FileReader(file1))
        var len: String? = null
        while (br.readLine().also { len = it } != null) {
            Toast.makeText(requireContext(), len, Toast.LENGTH_SHORT).show()
        }
        br.close()
    }

    private fun r(file1: File?) {
        if (!isExternalStorageWritable()) {
            Toast.makeText(requireContext(), "外部存储不可用", Toast.LENGTH_SHORT).show()
            return
        }
        val bw = BufferedWriter(FileWriter(file1))
        bw.write("hi")
        bw.close()
    }
}