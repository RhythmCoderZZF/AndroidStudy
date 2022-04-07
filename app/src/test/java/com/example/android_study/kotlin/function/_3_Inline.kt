package com.example.android_study.kotlin.function

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/15
 * Description:内联函数
 */
class _3_Inline {
    /**
     * 1.编译器对内联高阶函数的优化
     */
    @Test
    fun main1() {
        //1.内联函数
        doFun()
        doFun1()
    }

    private fun doFun() {
        i {
            return
        }
    }

    private fun doFun1() {
        i1 {
            return
        }
    }

    private inline fun i(action: () -> Unit) {
        println("hi")
        action()
    }

    private inline fun i1(action: () -> Unit) {
        action()
        println("hello")
    }

    /**
     * 2.内联函数中的return问题
     */
    @Test
    fun main() {
        f {
            return@f
        }
    }

    //crossinline禁止non-local return
    private inline fun f(crossinline action: () -> Unit): Runnable {
        return Runnable {
            action()
        }
    }

}