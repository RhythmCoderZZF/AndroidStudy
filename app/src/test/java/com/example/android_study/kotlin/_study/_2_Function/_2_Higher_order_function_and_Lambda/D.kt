package com.example.android_study.kotlin._study._2_Function._2_Higher_order_function_and_Lambda

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:
 */
class D {
    val sum = fun Int.(other: Int): Int = this + other
    init {
        sum(1,2)
    }

}