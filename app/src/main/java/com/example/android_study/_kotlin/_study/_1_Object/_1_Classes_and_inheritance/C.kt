package com.example.android_study._kotlin._study._1_Object._1_Classes_and_inheritance

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:派生类初始化顺序
 */
class C {
    open class Base(val name: String) {

        init {
            println("Initializing Base")
        }

        open val size: Int =
                name.length.also { println("Initializing size in Base: $it") }
    }

    //
    class Derived(name: String, val lastName: String) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

        init {
            println("Initializing Derived")
        }

        override val size: Int =
                (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
    }
}