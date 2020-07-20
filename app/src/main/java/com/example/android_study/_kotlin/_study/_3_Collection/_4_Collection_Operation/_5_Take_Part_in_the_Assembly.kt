package com.example.android_study._kotlin._study._3_Collection._4_Collection_Operation

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description: 取集合的一部分
 */
fun main() {
    println("~~~~~~~~~~~~~~slice 取区间~~~~~~~~~~~~~~~~")
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3))
    println(numbers.slice(0..4 step 2))
    println(numbers.slice(setOf(3, 5, 0)))

    println("~~~~~~~~~~~~~~Take 与 drop 获取与丢弃指定的区间~~~~~~~~~~~~~~~~")
    println(numbers.take(3))
    println(numbers.takeLast(3))
    println(numbers.drop(1))
    println(numbers.dropLast(5))

    println("~~~~~~~~~~~~~~Chunked 分解集合为指定的块~~~~~~~~~~~~~~~~")
    println(numbers.chunked(4))


    println("~~~~~~~~~~~~~~Windowed 滑动窗口分解集合为指定的块~~~~~~~~~~~~~~~~")
    val numbers1 = (1..10).toList()
    println(numbers1.windowed(3, step = 2, partialWindows = false))
    println(numbers1.windowed(3) { it.sum() })
}
