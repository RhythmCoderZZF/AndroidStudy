package com.example.android_study._kotlin._study._3_Collection._4_Collection_Operation

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:
 */
fun main() {
    val numbers = listOf("one", "two", "three", "four", "five")
    println(numbers.groupBy { it.first().toUpperCase() })
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
}