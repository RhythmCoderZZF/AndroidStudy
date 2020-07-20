package com.example.android_study._kotlin._study._1_Object._10_object

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:匿名对象
 */
class A {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    init {
        foo().x

        publicFoo()
    }
}