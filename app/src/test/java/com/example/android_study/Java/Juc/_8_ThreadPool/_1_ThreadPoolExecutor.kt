package com.example.android_study.Java.Juc._8_ThreadPool

import org.junit.Test
import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * Author: create by RhythmCoder
 * Date: 2020/8/8
 * Description:
 */
class _1_ThreadPoolExecutor {
    private val CORE_POOL_SIZE = 2
    private val MAX_POOL_SIZE = 4
    private val QUEUE_CAPACITY = 10
    private val KEEP_ALIVE_TIME = 2L

    //使用阿里巴巴推荐的创建线程池的方式
    //通过ThreadPoolExecutor构造函数自定义参数创建
    val executor = ThreadPoolExecutor(
        CORE_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_ALIVE_TIME,
        TimeUnit.SECONDS,
        ArrayBlockingQueue(QUEUE_CAPACITY),
        ThreadPoolExecutor.CallerRunsPolicy()
    )


    inner class MyRunnable(val command: String) : Runnable {
        override fun run() {
            println("线程：$command" + " Start. Time = " + Date());
            processCommand();
            println("线程：$command" + " End. Time = " + Date());
        }

        private fun processCommand() {
            try {
                Thread.sleep(5000);
            } catch (e: InterruptedException) {
                e.printStackTrace();
            }
        }

        override fun toString() = command
    }

    /**
     *  tasks小于容量
     */
    @Test
    fun t() {
        for (i in 1..14) {
            val worker: Runnable = MyRunnable("" + i)
            //执行Runnable
            executor.execute(worker)
        }
        //终止线程池
//        executor.shutdown()
        while (!executor.isTerminated) {
            Thread.sleep(500)
            println("线程数：${executor.poolSize}")
        }
        println("Finished all threads Time:" + Date())
    }

    /**
     * 测试核心线程退出
     */
    @Test
    fun t1() {
        executor.apply {
            allowCoreThreadTimeOut(true)
        }
        for (i in 1..14) {
            val worker: Runnable = MyRunnable("" + i)
            //执行Runnable
            executor.execute(worker)
        }
        //终止线程池
//        executor.shutdown()
        while (!executor.isTerminated) {
            Thread.sleep(500)
            println("线程数：${executor.poolSize}")
        }
        println("Finished all threads Time:" + Date())
    }

    /**
     * 测试已有核心线程，但是线程数小于核心线程数，提交新任务是会使用原有的核心线程还是创建新的核心线程
     */
    @Test
    fun t2() {
        val worker: Runnable = MyRunnable("哈哈")
        executor.execute(worker)
        TimeUnit.SECONDS.sleep(6)
        val worker1: Runnable = MyRunnable("哦哦")
        executor.execute(worker1)
//        executor.shutdown()
        while (!executor.isTerminated) {
            Thread.sleep(500)
            println("线程数：${executor.poolSize}")
        }
        println("Finished all threads Time:" + Date())
    }
}