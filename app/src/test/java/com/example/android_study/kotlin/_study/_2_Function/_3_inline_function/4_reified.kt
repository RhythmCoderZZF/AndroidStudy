package com.example.android_study.kotlin._study._2_Function._3_inline_function

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/4
 * Description:具体化的类型参数
 */

class `4_reified` {
    inline fun <reified T> f() = T::class.java

    init {
        print(f<String>().simpleName)
    }


}

fun main() {
    `4_reified`()
}