package com.example.android_study.kotlin._study._1_Object._4_extend

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:扩展
 */
class A {
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // “this”对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }

    /**
     * 我们想强调的是扩展函数是静态分发的，即他们不是根据接收者类型的虚方法。
     * 这意味着调用的扩展函数是由函数调用所在的表达式的类型来决定的， 而不是由表达式运行时求值结果决定的
     */
    open class Shape

    class Rectangle : Shape()

    fun Shape.getName() = "Shape"

    fun Rectangle.getName() = "Rectangle"

    fun printClassName(s: Shape) {
        println(s.getName())
    }

    init {
        printClassName(Rectangle())
    }


}