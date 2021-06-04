package com.example.android_study.android.data_and_file.internal_and_outspace

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.fragment_android_data_and_file_internal_space.*
import java.io.*
import java.lang.StringBuilder

/**
 * Author:create by RhythmCoder
 * Date:2021/3/9
 * Description:
 */
class InternalFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_android_data_and_file_internal_space,
            container,
            false
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dir = requireContext().filesDir// data/user/0/[package]/files
        val dir1 = requireContext().cacheDir// data/user/0/[package]/cache
        val dir2 = requireContext().codeCacheDir// data/user/0/[package]/code_cache
        val dir3 = requireContext().noBackupFilesDir// data/user/0/[package]/no_backup
        val dir4 = requireContext().dataDir// data/user/0/[package]
        val dir5 = Environment.getDataDirectory()// data
        val dir6 = Environment.getRootDirectory()// system

        val hahaDirPath = dir4.absolutePath + File.separatorChar + "haha"
        val hahaDir = File(hahaDirPath)
        if (!hahaDir.exists()) {
            hahaDir.mkdir()
        }

        CmdUtil.v(dir.absolutePath + "~")
        CmdUtil.v(dir1.absolutePath + "~")
        CmdUtil.v(dir2.absolutePath + "~")
        CmdUtil.v(dir3.absolutePath + "~")
        CmdUtil.v(dir4.absolutePath + "~")
        CmdUtil.v(dir5.absolutePath + "~")
        CmdUtil.v(dir6.absolutePath + "~")

        btnWrite.setOnClickListener {
            val fos = FileWriter(File(hahaDirPath + File.separatorChar + "hi.txt"), true)
            fos.write("hello world")
            fos.close()
        }

        btnRead.setOnClickListener {
            val fr = FileReader(File(hahaDirPath + File.separatorChar + "hi.txt"))
            val bfr = BufferedReader(fr)
            var len: String? = null
            val sb = StringBuilder()
            while (bfr.readLine().also { len = it } != null) {
                sb.append(len)
            }
            Toast.makeText(requireContext(), "$sb", Toast.LENGTH_SHORT).show()
            bfr.close()
        }


    }
}