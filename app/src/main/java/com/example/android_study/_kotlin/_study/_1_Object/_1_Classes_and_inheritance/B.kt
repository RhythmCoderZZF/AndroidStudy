package com.example.android_study._kotlin._study._1_Object._1_Classes_and_inheritance

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description: 继承,覆盖方法、属性
 *
 */
class B {
    /**
     * 开放继承
     */
    open class Base(p: Int)//open 去除final修饰
    class Derived(p: Int) : Base(p)

    /**
     * 覆盖方法、属性
     */
    open class Shape {
        open val vertexCount: Int = 1
        open fun draw() {}
        fun fill() {}
    }

    class Circle() : Shape() {
        override val vertexCount = 4
        override fun draw() {} //必须加override，标记为 override 的成员本身是开放的
    }
}