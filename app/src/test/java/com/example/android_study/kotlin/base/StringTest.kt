package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/14
 * Description: String的一些知识
 */
class StringTest {
    @Test
    fun main() {

        //1.==和===
        val s1 = "a"
        val s2 = String("a".toCharArray())
        println("${s1 == s2}")//true
        println("${s1 === s2}")//false
    }
}