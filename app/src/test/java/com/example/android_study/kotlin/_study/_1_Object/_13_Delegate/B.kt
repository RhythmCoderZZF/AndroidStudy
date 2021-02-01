package com.example.android_study.kotlin._study._1_Object._13_Delegate

import org.junit.Test
import kotlin.reflect.KProperty

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:
 * 委托属性
 *
 */
class B {
    /*-----------------委托属性的原理----------------------*/
    class Example {
        /**
         * 通过by, p属性的getP()/setP()方法被Delegate的getValue()/setValue委托
         * 因此可以自定义委托类来自定义属性get/set的场景（比如Lazy）
         */
        var p: String by Delegate()
    }

    class Delegate {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
            return "$thisRef, thank you for delegating '${property.name}' to me!"
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
            println("$value has been assigned to '${property.name}' in $thisRef.")
        }
    }

    @Test
    fun t() {
        val e = Example()
        println(e.p)
        e.p = "><><>"
    }

    /*-----------------委托属性的用法Lazy----------------------*/
    private val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    @Test
    fun main() {
        /*  computed!
            Hello
            Hello
         */
        println(lazyValue)
        println(lazyValue)
    }
}
