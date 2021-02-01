package com.example.android_study.kotlin._study._1_Object._7_Generics

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description: 协变in（方法参数）、逆变out（方法返回）
 */

class A {
    interface SourceOut<out T> {
        fun outT(): T   //yes：只能输出T，不能输入（保证不会被其他T的子类篡改而发生类型转换异常）
        //      fun inT(t:T)       error
    }

    interface SourceIn<in T> {
        //      fun outT(): T   // error
        fun inT(t: T)   //yes：只能输出T，不能输入（保证不会被其他T的子类接受而发生类型转换异常）
    }
// 集合框架大量用到in out——对于容器来说in表示泛型元素只能被添加、删除到集合容器；out表示泛型元素只能被集合容器遍布输出
}