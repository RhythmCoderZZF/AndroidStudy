package com.example.android_study._kotlin._study._2_Function._2_Higher_order_function_and_Lambda

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description: Lambda
 */
class C {

    init {
        //1. lambda完整语法
        var sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }            //等价于sum1写法
        var sum1: (Int, Int) -> Int = fun(x: Int, y: Int): Int { return x + y }

        var sum2 = { x: Int, y: Int -> x + y }                              // 简写

        //2.传递末尾的 lambda 表达式
        //2.1 在 Kotlin 中有一个约定：如果函数的最后一个参数是函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外
        val product = listOf(1, 2).fold(1) { acc, e -> acc * e }

        //2.2 如果该 lambda 表达式是调用时唯一的参数，那么圆括号可以完全省略
        run { println("...") }

        //3. it：单个参数的隐式名称
        listOf(1, 2).filter { it > 1 }                                      // 这个字面值是“(it: Int) -> Boolean”类型的


    }

    fun f(l: () -> Unit) {}
}