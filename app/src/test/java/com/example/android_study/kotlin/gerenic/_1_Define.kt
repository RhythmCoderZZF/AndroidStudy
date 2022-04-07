package com.example.android_study.kotlin.gerenic

import com.example.android_study.R
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/26
 * Description:
 */
class _1_Define {
    @Test
    fun main() {
    }

    private fun <T : Comparable<T>> maxOf(a: T, b: T): T {
        return if (a > b) a else b
    }

    private class User<T, R>
}