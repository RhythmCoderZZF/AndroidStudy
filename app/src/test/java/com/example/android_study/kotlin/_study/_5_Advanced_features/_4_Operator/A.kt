package com.example.android_study.kotlin._study._5_Advanced_features._4_Operator

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/6
 * Description:操作符重载
 *
 * Kotlin 允许我们为自己的类型提供预定义的一组操作符的实现。这些操作符具有固定的符号表示 （如 + 或 *）和固定的优先级。
 * 为实现这样的操作符，我们为相应的类型（即二元操作符左侧的类型和一元操作符的参数类型）提供了一个固定名字的成员函数或扩展函数。
 * 重载操作符的函数需要用 operator 修饰符标记。
 */

operator fun String.unaryPlus(): String {
    return "$this plus"
}

fun main() {
    print(+"ha")
}