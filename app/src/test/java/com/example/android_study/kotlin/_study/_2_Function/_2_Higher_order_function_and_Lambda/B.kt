package com.example.android_study.kotlin._study._2_Function._2_Higher_order_function_and_Lambda

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:高阶函数——高阶函数是将函数用作参数或返回值的函数
 */
class B {
    private fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    init {
        var f = fun(a: Int, _: Int): Int { return a }
        listOf(1, 2, 3).fold(0, f)
    }
}