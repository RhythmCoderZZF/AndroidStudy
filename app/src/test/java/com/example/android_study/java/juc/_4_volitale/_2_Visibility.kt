package com.example.android_study.java.juc._4_volitale

import org.junit.Test
import kotlin.concurrent.thread

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 *
 * 测试volatile的可见性
 */
class _2_Visibility {
    @Volatile
    var a = true
//    @Volatile
    var b = false
    var b1 = false
    var b2 = false

    @Test
    fun t() {
        MutableList(5) {
            thread {
                while (a) {
                    Thread.yield()
                }
                println("$b $b1 $b2")
            }
        }
        Thread.sleep(10)

        b = true
        b1 = true

        Thread.yield()
        a = false
        b2 = true
    }

}