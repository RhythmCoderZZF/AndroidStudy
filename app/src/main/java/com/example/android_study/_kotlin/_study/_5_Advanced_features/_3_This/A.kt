package com.example.android_study._kotlin._study._5_Advanced_features._3_This

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/6
 * Description:
 * 为了表示当前的 接收者 我们使用 this 表达式：

在类的成员中，this 指的是该类的当前对象。
在扩展函数或者带有接收者的函数字面值中， this 表示在点左侧传递的 接收者 参数。
如果 this 没有限定符，它指的是最内层的包含它的作用域。要引用其他作用域中的 this，请使用 标签限定符：
 */
class A { // 隐式标签 @A
    inner class B { // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@A // A 的 this
            val b = this@B // B 的 this

            val c = this // foo() 的接收者，一个 Int
            val c1 = this@foo // foo() 的接收者，一个 Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者
            }


            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }
    }
}