package com.example.android_study.kotlin._study._1_Object._1_Classes_and_inheritance

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/1
 * Description:抽象类
 */
class E {

    open class Polygon {
        open fun draw() {}
    }

    abstract class Rectangle : Polygon() {
        abstract override fun draw()
    }
}