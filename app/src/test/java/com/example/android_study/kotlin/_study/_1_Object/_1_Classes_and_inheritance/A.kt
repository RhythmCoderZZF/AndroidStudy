package com.example.android_study.kotlin._study._1_Object._1_Classes_and_inheritance

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description: 类、构造函数、次构造函数
 */


class A {
    /**
     * 类
     */
    private class Empty //private static final class Empty

    /**
     * 构造函数
     */
    private class G(firstName: String)

    /**
     * 次构造函数
     */
    private class Person {
        var children: MutableList<Person> = mutableListOf()

        //相当于主构造函数
        constructor(parent: Person) {
            parent.children.add(this)
        }
    }

    private class PersonX(val name: String) {
        var children: MutableList<PersonX> = mutableListOf()

        //次构造函数，先执行this指向的主构造函数
        constructor(name: String, parent: PersonX) : this(name) {
            parent.children.add(this)
        }
    }

    class Constructors {
        init {
            println("Init block")
        }

        constructor(i: Int) {
            println("Constructor")
        }
    }

    /*
    如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值。
    调用无参构造函数将使用初始化的默认值
    */
    class Customer(val customerName: String = "Customer")
}