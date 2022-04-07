package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/14
 * Description: 数组
 */
class _2_Array {
    @Test
    fun main() {
        //1.数组
        val a1 = IntArray(5)
        val a2 = intArrayOf(0, 1, 2)
        val a3 = arrayOf(0, 1, 2)

        for (i in a3) {
            print(i)
        }
        println()
        for (i in a3.indices) {
            print(i)
        }
    }
}