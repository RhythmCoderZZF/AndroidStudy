package com.example.android_study.kotlin.function

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/15
 * Description:Lambda表达式
 */
class _2_Lambda {
    @Test
    fun main() {
        //1.匿名函数
        val f: () -> Unit = fun() {
            println("我是匿名函数")
        }
        f()

        //2.Lambda(就是匿名函数的简写)
        val f1: (Int) -> String = {
            "$it ->${it + 10}"
        }
    }
}