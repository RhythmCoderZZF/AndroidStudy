package com.example.android_study._kotlin._study._1_Object._8_Nest_class

import android.view.View

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:嵌套类，内部类，匿名内部类
 *
 */
class A {
    class B {
        fun b() {}
    }

    inner class C {
        fun c() {}
    }

    init {
        //匿名类
        a(object :Listener{
            override fun onClick(v: View) {
            }
        })
    }

    fun a(listener: Listener) {

    }

    interface Listener {
        fun onClick(v: View)
    }
}