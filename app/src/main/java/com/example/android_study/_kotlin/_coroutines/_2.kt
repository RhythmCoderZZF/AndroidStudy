package com.example.android_study._kotlin._coroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/15
 * Description:
 */
//fun main() {
//    runBlocking {
//        val launch = launch {
//            delay(100)
//            println("...")
//        }
//        //1.  协程取消
//        launch.cancel()
//        println("cancel")
//    }
//
//    runBlocking {
//        //2. async
//        //2.1 创建新的协程并立刻执行
//        //2.2 返回一个deferred对象，若要获取async的执行结果，则调用await阻塞当前协程直到获取到计算结果
//        val result = async {
//            delay(1000)
//            5 * 5
//        }.await()
//        println(result)
//        val result1 = async {
//            delay(1000)
//            5 * 1
//        }.await()
//        println(result1)
//
//        //3. async模拟并发执行并获取结果
//        val deferred = async {
//            delay(100)
//            1
//        }
//        println("deferred")
//        val deferred1 = async {
//            delay(2000)
//            2
//        }
//        println("deferred 1")
//        println("${deferred.await()} ${deferred1.await()}")
//
//        //4. withContext() async await的简化写法
//        //4.1  调用该函数会立刻执行，将最后一行代码的执行结果最为返回值 = async{}.await()
//        //4.2  强制传入线程参数（Default=低并发线程策略 IO=gap并发线程策略 Main=不开启子线程）
//        runBlocking {
//            var result = withContext(Dispatchers.IO) {
//                delay(1000)
//                10010
//            }
//            println(result)
//        }
//
//    }
//}

fun main() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // 假设我们在这里做了些有用的事
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了些有用的事
//    throw RuntimeException("err")
    return 29
}



