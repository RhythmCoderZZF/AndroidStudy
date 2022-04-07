package com.example.android_study.kotlin.clazz

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/15
 * Description:类和接口
 */
class _1_Class {
    @Test
    fun main() {
        //1.继承，属性，重写，属性引用
        val ceo = CEO("张三", 30)

        val nameRef = Person::name//未绑定Receiver的属性引用
        println(nameRef.get(ceo))//需要传入Receiver

        val ageRef = ceo::age//绑定了Receiver的属性引用
        ageRef.set(25)//不需要传入Receiver
        ceo.doWork()

        //2.主从构造器
        val c1 = Constructor("李四", 11)
        println(c1)
    }

    private interface Person {
        val name: String
        fun doWork()
    }

    private abstract class Staff(override val name: String) : Person

    private class CEO(override val name: String, var age: Int) : Staff(name) {
        override fun doWork() {
            println("I am CEO,my name is $name,my age is $age")
        }
    }

    private class Constructor() {
        var name = ""
        var age = 0

        constructor(name: String) : this() {
            this.name = name
        }

        constructor(name: String, age: Int) : this(name) {
            this.age = age
        }

        override fun toString(): String {
            return "Constructor(name='$name', age=$age)"
        }

    }
}