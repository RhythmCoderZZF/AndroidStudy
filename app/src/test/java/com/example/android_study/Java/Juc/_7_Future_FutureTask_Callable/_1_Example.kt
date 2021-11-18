package com.example.android_study.Java.Juc._7_Future_FutureTask_Callable

import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

/**
 * Author:create by RhythmCoder
 * Date:2021/7/6
 * Description:
 */
class _1_Example {
    /**
     * Runnable
     */
    private inner class MyRunnable : Runnable {
        override fun run() {
            println("子线程开始执行...")
            Thread.sleep(2000)
            println("子线程开始执行完毕")
        }
    }

    /**
     * Callable
     */
    inner class MyCallable : Callable<String> {
        override fun call(): String {
            println("子线程开始执行...")
            Thread.sleep(2000)
            println("子线程开始执行完毕")
            return Thread.currentThread().name
        }
    }

    //_________________________submit(Callable())________________________________________
    @Test
    fun t() {
        val executor = Executors.newSingleThreadExecutor()
        val future = executor.submit(MyCallable())
        println("结果:${future.get()}")

    }

    //_________________________submit(Runnable)________________________________________
    @Test
    fun t1() {
        val executor = Executors.newSingleThreadExecutor()
        val future = executor.submit(MyRunnable())
        println("结果:${future.get()}")

    }


    //_________________________submit(FutureTask(Callable))________________________________________
    @Test
    fun t2() {
        val executor = Executors.newSingleThreadExecutor()
        val futureTask = FutureTask(MyCallable())
        executor.submit(futureTask)
        println("结果:${futureTask.get()}")
    }

    //_________________________submit(FutureTask(Runnable))________________________________________
    @Test
    fun t3() {
        val executor = Executors.newSingleThreadExecutor()
        val futureTask = FutureTask(MyRunnable(), "111")
        executor.submit(futureTask)
        println("结果:${futureTask.get()}")
    }
    //_________________________cancel Task________________________________________
    @Test
    fun t4() {
        val executor = Executors.newSingleThreadExecutor()
        val future=executor.submit(MyCallable())

        Thread.sleep(1000)
        if (!future.isDone) {
            println("取消任务:${future.cancel(true)}")
        }
        println("结果:${future.get()}")
    }


}