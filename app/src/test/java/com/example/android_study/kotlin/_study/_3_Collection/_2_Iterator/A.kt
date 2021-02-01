package com.example.android_study.kotlin._study._3_Collection._2_Iterator

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:
 */
fun main() {
    val listOf = mutableListOf(1, 2, 3)
    val iterator = listOf.listIterator()
    while (iterator.hasNext()) {
        iterator.next()
        iterator.set(4)
        print(listOf[iterator.previousIndex()])
    }
    println()

    for (item in listOf) {
        print(item)
    }
    println()

    listOf.forEach { print(it) }
    println()
}
