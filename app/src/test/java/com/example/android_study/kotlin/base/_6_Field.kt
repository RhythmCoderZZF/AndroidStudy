package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/23
 * Description:JvmField
 */
class _6_Field {

    var a = 0

    @JvmField
    var b = 1

    @Test
    fun main() {

    }

    class T {
        var a = 0

        @JvmField
        var b = 0



        companion object {
            var c = 2

            @JvmField
            var d = 3

            @JvmStatic
            var e = 3
        }
    }

}