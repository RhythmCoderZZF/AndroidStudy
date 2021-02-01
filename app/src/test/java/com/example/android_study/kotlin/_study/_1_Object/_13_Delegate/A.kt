package com.example.android_study.kotlin._study._1_Object._13_Delegate

import org.junit.Test

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:
 * 委托
 * 1. Derived和BaseImpl必须实现同一个接口Base
 * 2. Derived的所有成员都委托给BaseImpl的实例对象
 */
class A {
    interface Base {
        fun print()
    }

    private class BaseImpl(private val x: Int) : Base {
        override fun print() {
            print(x)
        }
    }

    private class Derived(b: Base) : Base by b

    init {
        val b = BaseImpl(10)
        val d = Derived(b)
        d.print()

    }

    @Test
    fun t() {
        A()
        Thread.sleep(12000)
    }
}