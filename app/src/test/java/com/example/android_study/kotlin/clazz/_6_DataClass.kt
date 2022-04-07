package com.example.android_study.kotlin.clazz

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/25
 * Description:
 */
class _6_DataClass {
    @Test
    fun main() {
        //1.数据类可以被结构
        val (name, age) = Person("Jack", 18)
        println("$name's age is $age")
    }

    data class Person(
        val name: String,
        val age: Int
    )
}