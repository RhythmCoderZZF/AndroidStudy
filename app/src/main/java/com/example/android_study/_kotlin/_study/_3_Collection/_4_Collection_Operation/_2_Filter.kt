package com.example.android_study._kotlin._study._3_Collection._4_Collection_Operation

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:
 */

fun main() {
    println("~~~~~~~~~~~~~~~~~~filter 过滤~~~~~~~~~~~~~~~~~")

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.filter { (k, v) -> k.length < 5 && v > 1 })


    println("~~~~~~~~~~~~~~~~~~partition 划分~~~~~~~~~~~~~~~~~")

    val numbers = listOf("one", "two", "three", "four")
    val (match, rest) = numbers.partition { it.length > 3 }
    println(match)
    println(rest)

    println("~~~~~~~~~~~~~~~~~~any、none、all 集合检测~~~~~~~~~~~~~~~~~")

    println(numbers.any { it.endsWith("e") })
    println(numbers.none { it.endsWith("a") })
    println(numbers.all { it.endsWith("e") })

    println(emptyList<Int>().all { it > 5 })   // vacuous truth

}