package com.example.android_study._kotlin._study._1_Object._4_extend

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:伴生对象的扩展
 */
class B {
    /**
     * 伴生对象的扩展
    如果一个类定义有一个伴生对象 ，你也可以为伴生对象定义扩展函数与属性。就像伴生对象的常规成员一样，
    可以只使用类名作为限定符来调用伴生对象的扩展成员：
     */
    class MyClass {
        companion object { }  // 将被称为 "Companion"
    }

    fun MyClass.Companion.printCompanion() { println("companion") }

    fun main() {
        MyClass.printCompanion()
    }
}