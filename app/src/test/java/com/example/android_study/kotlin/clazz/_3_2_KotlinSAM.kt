package com.example.android_study.kotlin.clazz

import com.example.android_study.R
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/20
 * Description:
 * Kotlin中的SAM转换指的是：使用lambda代替Java接口，而无法代替Kotlin接口
 */
class _3_2_KotlinSAM {
    @Test
    fun main() {
        //1.lambda代替Java接口
        f {}

        //2.lambda代替kotlin接口就会报错
//        f1 {}
    }

    private fun f(action: Runnable) {
        action.run()
    }

    private fun f1(action: Runnable1) {
        action.run()
    }

    private interface Runnable1 {
        fun run()
    }

    @Test
    fun main1() {
        //还是使用匿名内部类，不使用lambda
        f1(object : Runnable1 {
            override fun run() {
            }
        })

        f2 {}
    }

    private fun f2(action: RunnableF) {
        action.run()
    }

    private fun interface RunnableF {
        fun run()
    }
}