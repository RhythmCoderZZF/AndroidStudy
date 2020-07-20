package com.example.android_study._kotlin._study._2_Function._2_Higher_order_function_and_Lambda

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:
 * 1. 函数类型（函数类型相当于Int、String，其实本质是一个Function接口类型）
 * 2. 函数类型实例化
 * 3. 函数类型实例调用
 */
class A {
    var s: Function<Any?>
    var b = A()

    init {
        //1. 函数类型(类似于声明f:Int)
        var f: () -> Unit


        //2. 实例化函数类型
        f = fun() {}                         //2.1 匿名函数
        f = {}                               //2.2 lambda(lambda 相当于 匿名函数)

        s = String::toString                 //2.3 使用已有声明的可调用引用
        s = ::A                              //2.3 引用构造函数
        s = b::foo                           //2.3 引用特定实例成员的绑定的可调用引用
        s = IntTransformer()                 //2.4 使用实现函数类型接口的自定义类的实例()


        //3. 函数类型实例调用(f.invoke(x) 或者直接 f(x))
        (s as IntTransformer).invoke(1)
        (s as IntTransformer)(1)
    }

    private fun foo() {}

    class IntTransformer : (Int) -> Int {
        override operator fun invoke(x: Int): Int = TODO()
    }

}