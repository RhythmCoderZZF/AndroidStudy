package com.example.android_study.kotlin._study._1_Object._2_Properties_and_fields

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:属性与字段
 */
open class A {

    var c = 0
        set(value) {
            field = value + 1
        }
        get() {
            return field--
        }
    val str1 = ""
        get() {
            return "s $field"
        }

    var array = arrayOf(1, 2)

    //
//    /**
//     * 延迟初始化属性与变量
//     * 该修饰符只能用于在类体中的属性（不是在主构造函数中声明的 var 属性，并且仅当该属性没有自定义 getter 或 setter 时），
//     * 而自 Kotlin 1.2 起，也用于顶层属性与局部变量。该属性或变量必须为非空类型，并且不能是原生类型。
//     */
//    lateinit var subject: View
//
//    init {
//    }
    class B : A() {

    }

}

fun main() {

}
