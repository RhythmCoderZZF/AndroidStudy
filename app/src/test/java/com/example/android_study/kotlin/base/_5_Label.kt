package com.example.android_study.kotlin.base

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/16
 * Description:
 */
class _5_Label {
    @Test
    fun main() {
        //1.break
        //跳出该标签指定的循环表达式
        out@ for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == 1 && j == 1) {
                    break@out
                }
                print("[$i,$j],")
            }
        }
        println()

        //2.continue
        //继续标签指定的循环的下一次迭代
        out@ for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (i == 1 && j == 1) {
                    continue@out
                }
                print("[$i,$j],")
            }
        }
        println()

        //3.return
        foo()//non-local return

        println()
        bar()//local return

        println()
        fizz()//local return

        //4.return(匿名函数)
        println()
        rezz()
    }

    fun foo() {
        repeat(3) {
            if (it == 1) return // 非局部直接返回到 foo() 的调用者
            print("$it,")
        }
    }

    fun bar() {
        repeat(3) {
            if (it == 1) return@repeat // 局部返回到该 lambda 表达式的调用者，即 repeat 循环
            print("$it,")
        }
    }

    fun fizz() {
        repeat(3) lambda@{
            if (it == 1) return@lambda // 局部返回到该 lambda 表达式的调用者，即 repeat 循环
            print("$it,")
        }
    }

    fun rezz() {
        repeat(3, fun(it: Int) {
            if (it == 1) return // 局部返回到匿名函数的调用者，即 repeat 循环
            print("$it,")
        })
    }
}