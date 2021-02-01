package com.example.android_study.kotlin._study._3_Collection._4_Collection_Operation

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:
 */

fun main() {
    val numbers = listOf("one", "two", "three", "four")

    val plusList = numbers + "five"
    val minusList = numbers - listOf("three", "four")
    println(plusList)
    println(minusList)
}