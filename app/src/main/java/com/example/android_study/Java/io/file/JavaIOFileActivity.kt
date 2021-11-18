package com.example.android_study.Java.io.file

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_java_io_file.*
import java.io.File

class JavaIOFileActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_java_io_file

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
    }

    override fun run() {
        btn_1.setOnClickListener {
            val separator = File.separator
            val pathSeparator = File.pathSeparator
            CmdUtil.v("separator>> $separator \n pathSeparator>> $pathSeparator")
        }
        btn_2.setOnClickListener {
            val file = File("/hello/zzf.txt")
            val file1 = File("/hello/word/zzf.txt")
            CmdUtil.v("file 绝对路径:${file.absolutePath}")
            CmdUtil.v("file1 绝对路径:${file1.absolutePath}")
            CmdUtil.v("file 相对路径:${file.path}")
            CmdUtil.v("file1 相对路径:${file1.path}")
        }
        btn_3.setOnClickListener {
            val file = File("a.txt")
//            if (!file.exists()) {
//                file.createNewFile()
//            }
            CmdUtil.v(file.absolutePath)
            CmdUtil.v(file.path)
        }

    }
}