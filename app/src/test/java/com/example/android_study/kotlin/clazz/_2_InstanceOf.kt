package com.example.android_study.kotlin.clazz

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/15
 * Description:类型转换
 */
class _2_InstanceOf {
    @Test
    fun main() {
        val c1 = ""
        val c2 = CEO("张三")
        println((c1 as? Person)?.name)//null
        println((c2 as? Person)?.name)//张三
    }

    private interface Person {
        val name: String
    }

    class CEO(override val name: String) : Person
}