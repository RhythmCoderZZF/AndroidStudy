package com.example.android_study._kotlin._study._5_Advanced_features._1_Deconstruction_Statement

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/4
 * Description:
 * 解构声明(Destructuring declarations)
 * 有时把一个对象 解构 成很多变量会很方便——这种语法称为 解构声明
 */
data class A(val i: Int, val k: String) {
    fun main() {
        //1. 解构声明
        val (i, k) = A(1, "")

        //2. 下划线用于未使用的变量
        var (_, r) = f()

        //3. 在 lambda 表达式中解构
        l { (z, x) -> print("$z  $x") }
    }

    fun f(): A {
        return A(0, "status")
    }

    fun l(block: (a: A) -> Unit) {
        block(A(11, "hello"))
    }
}

fun main() {
    A(1, "1").main()
}

