package com.example.android_study.java.IO

import org.junit.Test
import java.io.*

/**
 * Author:create by RhythmCoder
 * Date:2020/12/26
 * Description:
 * 1. Buffered内部维护一个缓冲区（字节/字符数组），读或者写不会每次把数据从物理介质传递到内存
 * 先
 */
class _4_InputOutputReaderWriterBuffer {

    /**
     *  410ms
     */
    @Test
    fun t() {
        val bis = BufferedInputStream(FileInputStream("bigFile.zip"))
        val bos = BufferedOutputStream(FileOutputStream("bigFile-copy.zip"))
        val startTime = System.currentTimeMillis()
        var byte = -1
        while (bis.read() != -1) {
            bos.write(byte)
            bos.flush()
        }
        val endTime = System.currentTimeMillis()
        println("耗时：${endTime - startTime}")
        bis.close()
        bos.close()
    }

    /**
     * 35ms
     */

    @Test
    fun t1() {
        val bis = BufferedInputStream(FileInputStream("bigFile.zip"))
        val bos = BufferedOutputStream(FileOutputStream("bigFile-copy.zip"))
        val startTime = System.currentTimeMillis()
        val byteArray = ByteArray(1024)
        var len = -1
        while (bis.read(byteArray).also { len = it } != -1) {
            bos.write(byteArray, 0, len)
        }
        val endTime = System.currentTimeMillis()
        println("耗时：${endTime - startTime}")
        bis.close()
        bos.close()
    }

    /**
     * 35ms
     */
    @Test
    fun t3() {
        val bis = FileInputStream("bigFile.zip")
        val bos = FileOutputStream("bigFile-copy.zip")
        val startTime = System.currentTimeMillis()
        val byteArray = ByteArray(1024 + 8192)
        var len = -1
        while (bis.read(byteArray).also { len = it } != -1) {
            bos.write(byteArray, 0, len)
        }
        val endTime = System.currentTimeMillis()
        println("耗时：${endTime - startTime}")
        bis.close()
        bos.close()
    }

    /**
     * BufferedReader/BufferedWriter
     */
    @Test
    fun t4() {
        val br = BufferedReader(FileReader("a.txt"))
        val bw = BufferedWriter(FileWriter("a-copy-buffer.txt"))

        var len: String? = null
        while (br.readLine().also { len = it } != null) {
            println(len)
            bw.write(len)
            bw.newLine()
        }
        br.close()
        bw.close()
    }
}