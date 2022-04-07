package com.example.android_study.kotlin.clazz

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/23
 * Description:
 */
class _5_Static {
    @Test
    fun main() {
    }



    object T1{
       public var a=0
        @JvmStatic var b=1
        fun x(){}
        @JvmStatic fun y(){}
    }

    private class T {
        companion object {
            fun a() {}

            @JvmStatic
            fun b() {
            }
        }
    }
}