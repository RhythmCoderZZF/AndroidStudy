package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/14
 * Description:区间
 */
class _3_Range {
    @Test
    fun main() {
        val b1 = 0..5
        val b2 = 0 until 5
        val b3 = 5 downTo 0
        println(b1.joinToString())
        println(b2.joinToString())
        println(b3.joinToString())
        val b4 = 0..5 step 2
        println(b4.joinToString())
    }
}