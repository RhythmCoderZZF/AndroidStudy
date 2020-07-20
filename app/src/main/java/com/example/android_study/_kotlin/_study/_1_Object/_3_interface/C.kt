package com.example.android_study._kotlin._study._1_Object._3_interface

import android.icu.text.Transliterator

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:接口继承
 */
class C {
    interface Named {
        val name: String
    }

    interface Person : Named {
        val firstName: String
        val lastName: String

        override val name: String get() = "$firstName $lastName"
    }

    data class Employee(
            // 不必实现“name”
            override val firstName: String,
            override val lastName: String,
            val position: Transliterator.Position
    ) : Person
}