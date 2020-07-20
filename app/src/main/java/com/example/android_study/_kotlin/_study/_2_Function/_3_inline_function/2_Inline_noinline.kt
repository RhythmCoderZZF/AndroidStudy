package com.example.android_study._kotlin._study._2_Function._3_inline_function

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/3
 * Description:内联函数inline noinline
 */
class `2_Inline_noinline` {
    val body = { 1 }


    inline fun <T> lock(lock: Lock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    inline fun <T> lock1(lock: Lock, noinline body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    init {


        //1. inline 编译器会将内联函数里的代码块替换调用的地方
        lock(ReentrantLock()) { body }

        //2 noinline 标记noinline的函数不会被替换合并，但是其余参数会被合并
        lock1(ReentrantLock()) { body }
    }
}