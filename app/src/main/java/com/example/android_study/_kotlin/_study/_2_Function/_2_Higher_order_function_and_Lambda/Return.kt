package com.example.android_study._kotlin._study._2_Function._2_Higher_order_function_and_Lambda

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:局部返回
 */
class Return {
    fun f(block: () -> Unit) {
        print("start")
        block
        print("end")
    }

    init {
        //lambda只允许局部返回
        f {
            print("block start")
            return@f
            print("block end")
        }
    }


}