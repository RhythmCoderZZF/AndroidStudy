package com.example.android_study.kotlin._study._2_Function._3_inline_function

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:
 * 局部返回和非局部返回
 */


fun main(str: Array<String>) {
    foo {
        println("block start")
        return@foo                  //lambda在非内联函数中只能局部返回，因为 lambda 表达式不能使包含它的函数返回
        println("block end")
    }

    println("!!!!!!!!!!!!!!!!!!!!!!!!!!")
    foo1 {
        println("block start")
        return                      //内联函数中可以全局返回
        println("block end")
    }
}

fun foo(block: () -> Unit) {
    println("start")
    block()
    println("end")
}

inline fun foo1(block: () -> Unit) {
    println("start")
    block()
    println("end")
}