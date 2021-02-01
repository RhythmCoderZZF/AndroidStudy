package com.example.android_study.kotlin._study._2_Function._1_function

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:
 */
class A {

    fun f(i: Int = 1) {}
    fun foo(vararg strings: String) { /*……*/
    }

    fun foo1(strings: Array<String>) { /*……*/
    }

    infix fun Int.shl(x: Int): Int {
        return 1
    }


    init {
        f()
        f(2)
        foo(strings = *arrayOf("a", "b", "c.txt"))
        foo1(strings = arrayOf("a", "b", "c.txt"))
        print(1 shl 3)
    }
}