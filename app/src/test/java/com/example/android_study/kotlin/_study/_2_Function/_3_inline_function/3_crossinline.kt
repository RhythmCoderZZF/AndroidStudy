package com.example.android_study.kotlin._study._2_Function._3_inline_function

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:
 */

//inline传入的lambda可以非局部返回，但是body在Runnable的嵌套函数中调用无法非局部返回(不能return掉Runnable.run())
// crossinline标记body
inline fun f(crossinline body: () -> Unit) {
    val c = object : Runnable {
        override fun run() {
            println("run start")
            body()
            println("run end")
        }
    }
    c.run()
}

fun main() {
    f {
        println("start")
        return@f
        println("end")
    }
}