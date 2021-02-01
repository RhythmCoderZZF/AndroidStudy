package com.example.android_study.kotlin._study._3_Collection._3_Interval_and_Sequence

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:区间和数列
 */
fun main() {
    //数列
    for (i in 1..8 step 2) print(i)     //.. 等于 rangeTo()
    println()
    for (i in 8 downTo 1 step 2) print(i)
    println()

    for (i in 1 until 10) {       // i in [1, 10), 10被排除
        print(i)
    }
    println()

    //区间
    val versionRange = Version(1, 11)..Version(1, 30)
    println(Version(0, 9) in versionRange)                      //versionRange.contains(Version(0,9))
    println(Version(1, 20) in versionRange)


    //序列：https://www.kotlincn.net/docs/reference/sequences.html
    val sequence = listOf(1, 2, 3).asSequence()
    sequence.filter { it < 3 }.map { println("hi $it");"hi $it" }.toList()
}

private class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        }
        return this.minor - other.minor
    }
}