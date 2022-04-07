package com.example.android_study.kotlin.function

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/14
 * Description:
 * 1.函数
 * 2.匿名函数
 */
class _1_Function {
    @Test
    fun main() {
        //1.函数的赋值
        val f1: Foo.(Int, String) -> Any? = Foo::bar
        val f2: (Foo, Int, String) -> Any? = Foo::bar//上述两者等价，Foo是bar的Receiver

        val f3 = ::foo

        //2.引用传递
        p(f2)
        p1(f3)
    }

    private fun p(f: Foo.(Int, String) -> Any?) {
        val value = f(Foo(), 1, "s")
        println(value)
    }

    private fun p1(f: () -> Unit) {
        f.invoke()
    }

    private class Foo {
        fun bar(p0: Int, p1: String): Any? {
            return "$p0 - $p1"
        }
    }

    private fun foo() {
        println("hello I am foo()")
    }

    @Test
    fun main1() {
        //匿名函数
        val f = fun() {

        }

        val f1 = {}
    }
}


