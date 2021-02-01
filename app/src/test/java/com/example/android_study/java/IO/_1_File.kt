package com.example.android_study.java.IO

import org.junit.Test
import java.io.File

/**
 * Author:create by RhythmCoder
 * Date:2020/12/26
 * Description:
 */
class _1_File{
    @Test
    fun t() {
        val file = File("a.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        println(file.absolutePath)
        println(file.path)
    }
}