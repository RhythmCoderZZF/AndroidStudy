package com.example.android_study.kotlin._study._5_Advanced_features._6_Reflect

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/6
 * Description:反射
 *
 * !!注意：要使用kotlin反射需要独自添加依赖"org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
 */
class A {
    init {
        //kotlin class
        var kClazz = A::class
        var kClazz1 = this.javaClass.kotlin

        println("${kClazz} - ${kClazz1}")

        //java class
        var jClazz = A::class.java
        var jClazz1 = this.javaClass
        println("${jClazz} - ${jClazz1}")

    }
}

fun main() {
    A()
}