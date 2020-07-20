package com.example.android_study._kotlin._study._1_Object._3_interface

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:覆盖冲突
 */
class D {

    interface A {
        fun foo() {
            print("A")
        }

        fun bar()
    }

    interface B {
        fun foo() {
            print("B")
        }

        fun bar() {
            print("bar")
        }
    }

    class C : A {
        override fun bar() {
            print("bar")
        }
    }

    class D : A, B {
        override fun foo() {
            super<A>.foo()
            super<B>.foo()
        }

        override fun bar() {
            super<B>.bar()
        }
    }
}