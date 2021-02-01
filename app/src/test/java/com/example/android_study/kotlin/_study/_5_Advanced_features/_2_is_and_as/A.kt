package com.example.android_study.kotlin._study._5_Advanced_features._2_is_and_as

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/4
 * Description:
 */
class A {
    fun <T> f(a: T) {
        if (a is String) {
            a.length
        }


//        var s = a as String           // a = null 报错
        var ss = a as String?

        var sss = a as? String          //安全的类型转换,转换失败返回null
        print("$ss $sss")
    }
}

fun main() {
    A().f(null)
}