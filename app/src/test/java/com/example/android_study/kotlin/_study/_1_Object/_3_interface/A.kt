package com.example.android_study.kotlin._study._1_Object._3_interface

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:接口
 */
class A {
    interface MyInterface {
        fun bar()
        fun foo() {
            // 可选的方法体
        }
    }

    /**
     * 实现接口
     */
    class Child : MyInterface {
        override fun bar() {
            // 方法体
        }
    }


}