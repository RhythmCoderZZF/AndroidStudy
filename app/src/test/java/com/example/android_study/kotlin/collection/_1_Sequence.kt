package com.example.android_study.kotlin.collection

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/20
 * Description:Sequence懒序列
 */
class _1_Sequence {
    val list = listOf(0, 1, 2, 3)

    @Test
    fun main() {
        list.filter { it > 1 }
            .map { it * 2 }
            .forEach {
                print("$it,")
            }

        println()
        list.asSequence()
            .filter { it > 1 }
            .map { it * 2 }
            .forEach {
                print("$it,")
            }

    }
}