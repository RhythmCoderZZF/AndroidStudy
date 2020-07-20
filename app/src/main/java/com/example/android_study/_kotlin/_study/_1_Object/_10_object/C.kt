package com.example.android_study._kotlin._study._1_Object._10_object

import java.io.Serializable

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/2
 * Description:伴生对象
 */

class C {
    //public static final C.Companion Companion=new C.Companion()

    init {
        C.f() //其实调用Companion对象的f()
    }

    companion object : Serializable {
        fun f() {}
    }
}