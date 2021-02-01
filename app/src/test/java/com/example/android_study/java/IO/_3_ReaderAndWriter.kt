package com.example.android_study.java.IO

import org.junit.Test
import java.io.FileInputStream
import java.io.FileReader
import java.io.FileWriter

/**
 * Author:create by RhythmCoder
 * Date:2020/12/26
 * Description:
 */
class _3_ReaderAndWriter {
    /**
     * 1. InputStream读取字符文本文件的问题
     * 2. 使用字符流读取字符文件
     */
    @Test
    fun t() {
        //一个中文（UTF-8）=3个字节
        val fis = FileInputStream("c.txt")
        var b = -1
        val listByte = mutableListOf<Byte>()
        while (fis.read().also { b = it } != -1) {
            listByte.add(b.toByte())
        }
        print(String(listByte.toByteArray()))
        fis.close()

        //1. 使用FileReader
        println("——————————————————————————1. 使用FileReader")
        val fr = FileReader("c.txt")
        var r: Char? = null
        while (fr.read().also { r = it.toChar() } != -1) {
            print(r)
        }
        fr.close()

        println()
        val fr1 = FileReader("c.txt")
        val charArray = CharArray(10)
        var len = -1
        while (fr1.read(charArray).also { len = it } != -1) {
            print(String(charArray, 0, len))
        }
        fr1.close()
    }

    /**
     * 2. 文本复制
     */
    @Test
    fun t1() {
        val fr = FileReader("c.txt")
        val fw = FileWriter("c-copy.txt",false)

        val charArray = CharArray(10)
        var len = -1
        while (fr.read(charArray).also { len = it } != -1) {
            fw.write(charArray,0,len)
            fw.flush()
        }
        fr.close()
        fw.close()
    }
}