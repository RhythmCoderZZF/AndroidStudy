package com.example.android_study._kotlin._study._1_Object._13_Delegate

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:
 */
class A {
    interface Base {
        fun print()
    }

    class BaseImpl(val x: Int) : Base {
        val i = 1
        override fun print() {
            print(x)
        }
    }

    class Derived(b: Base) : Base by b {
        val i = 2
    }

    init {
        val b = BaseImpl(10)
        val d = Derived(b)
        d.print()
        d.i
    }


}