package com.example.android_study.kotlin._study._1_Object._3_interface

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:接口属性
 */
class B {
    /**
     * 你可以在接口中定义属性。在接口中声明的属性要么是抽象的，要么提供访问器的实现。
     * 在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们
     */
    interface MyInterface {
        val prop: Int // 抽象的

        val propertyWithImplementation: String
            get() = "foo"

        fun foo() {
            print(prop)
        }
    }

    class Child : MyInterface {
        override val prop: Int = 29
    }

}