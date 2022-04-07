package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/14
 * Description:集合
 */
class _4_Collection {
    @Test
    fun main() {
        //1.创建
        val l1 = listOf(1, 2, 3)//不可变，不能添加删除元素
        val l2 = mutableListOf(1, 2, 3)
        val l3 = ArrayList<Int>()

        val m1 = mapOf("1" to "a", 2 to "b")
        val m2 = mutableMapOf("1" to "a", 2 to "b")

        //2.读写
        println(m1["1"])
    }
}