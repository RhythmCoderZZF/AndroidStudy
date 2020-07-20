package com.example.android_study._kotlin._coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/14
 * Description:
 *
 */
fun main() {
    val time = measureTimeMillis {
        // 我们可以在协程外面启动异步执行
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // 但是等待结果必须调用其它的挂起或者阻塞
        // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne1()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo1()
}

suspend fun doSomethingUsefulOne1(): Int {
    delay(1000L) // 假设我们在这里做了些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo1(): Int {
    delay(1000L)
//    throw RuntimeException("error")
    return 29
}