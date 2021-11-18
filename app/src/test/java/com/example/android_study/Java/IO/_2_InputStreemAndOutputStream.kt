package com.example.android_study.Java.IO

import org.junit.Test
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Author:create by RhythmCoder
 * Date:2020/12/26
 * Description:
 */
class _2_InputStreemAndOutputStream {
    /**
     * 1. 字节输出流
     */
    @Test
    fun t() {
        val fos = FileOutputStream("a.txt")
        //1. 写一个byte
        fos.write(65)//0-127会找ASCII码,其他值会查询系统编码表如UTF-8
        //2. 写一个byteArray
        val bytes = byteArrayOf(66, 67)
        fos.write(bytes)
        //3. 写指定区间的byteArray
        val bytes1 = byteArrayOf(65, 66, 67, 68, 69)
        fos.write(bytes1, 3, 2)
        //4. 写字符串
        val nihao = "你好".toByteArray()
        nihao.forEach {
            println(it) //UTF-8每个中文用三个byte表示，且都是负数
        }
        fos.write(nihao)
        //5. 写换行
        val haha = "哈哈\r\n".encodeToByteArray()//windows:\r\n  linux:/n mac:/r
        repeat(5) {
            fos.write(haha)
        }
        fos.close()
    }

    /**
     * 2. 字节输入流
     */
    @Test
    fun t1() {
        val fis = FileInputStream("a.txt")
        //1. 读取一个byte
        val len = fis.read()//1. 读完后，指针会自动下移到下一个字节 2.返回的len=读取的字节
        println(len.toChar())
        val len1 = fis.read()
        println(len1.toChar())
        //2. 循环,每次次读取一个字节
        var len2: Int = -1
        while (fis.read().also { len2 = it } != -1) {
            print(len2.toChar())
        }
//        while ({ len2 = fis.read();len2 }.invoke() != -1) {//kotlin的闭包写法，神奇！
//            print(len2.toChar())
//        }

        fis.close()
        //3. 缓冲读（一次性读到一个byteArray）
        println("————————————————3. 一次读取指定大小的字节数组")
        var fis1 = FileInputStream("b.txt")
        val readBytes = ByteArray(2)
        var lenRead = -1
        lenRead = fis1.read(readBytes)//1. 读完后，指针会自动下移到读取的最后一个字节位置 2.返回的len=成功读取的字节个数
        println(String(readBytes) + "-len:$lenRead")
        lenRead = fis1.read(readBytes)
        println(String(readBytes) + "-len:$lenRead")
        lenRead = fis1.read(readBytes)
        println(String(readBytes) + "-len:$lenRead")
        lenRead = fis1.read(readBytes)
        println(String(readBytes) + "-len:$lenRead")
        fis1.close()

        readBytes[0] = 0
        readBytes[1] = 0
        //4. 缓冲有效读（循环读到一个byteArray）
        println("————————————————4. 循环读")
        fis1 = FileInputStream("b.txt")
        while (fis1.read(readBytes).also { len2 = it } != -1) {
            print(String(readBytes,0,len2))
        }
        fis1.close()
    }

    /**
     * 3. 文件复制
     */
    @Test
    fun t2() {
        val fis = FileInputStream("key.jks")
        val fos = FileOutputStream("key-copy.jks")
        val byteArray = ByteArray(1024)
        var len = -1
        while (fis.read(byteArray).also { len = it } != -1) {
            fos.write(byteArray,0,len)
        }
        fis.close()
        fos.close()
    }
}