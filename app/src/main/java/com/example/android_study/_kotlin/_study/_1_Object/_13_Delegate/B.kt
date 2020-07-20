package com.example.android_study._kotlin._study._1_Object._13_Delegate

import kotlin.reflect.KProperty

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:委托属性
 */
class B {
    val p by Delegate()

    class Delegate {
        operator fun getValue(b: B, property: KProperty<*>): String {
            return ""
        }


    }
    init{
        print(p)
    }
}
