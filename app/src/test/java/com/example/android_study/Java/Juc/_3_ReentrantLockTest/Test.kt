package com.example.android_study.Java.Juc._3_ReentrantLockTest

import org.junit.Test
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/4
 * Description:
 *
 */

class Test {
    /**
     *  测试 可打断性
     */
    @Test
    fun t() {
        val lock = ReentrantLock()
        val t = thread {
            Thread.sleep(10)

            try {
                lock.lockInterruptibly()
            } catch (e: InterruptedException) {
                println("Interrupted !")
                return@thread
            }
            println("thread ...")
            lock.unlock()
        }

        try {
            lock.lock()
            Thread.sleep(1000)
            println("main ...")
            t.interrupt()
        } finally {
            lock.unlock()
        }
        t.join()
    }

    /**
     * 测试 超时获取锁
     */
    @Test
    fun t1() {
        val lock = ReentrantLock()
        val t = thread {
            Thread.sleep(10)

            try {
                if (!lock.tryLock(500,TimeUnit.MILLISECONDS)) {
                    println("thread 尝试获取锁失败！")
                    return@thread
                }
            } catch (e: InterruptedException) {
                println("Interrupted !")
                return@thread
            }
            println("thread ...")
            lock.unlock()
        }

        try {
            lock.lock()
            Thread.sleep(1000)
            println("main ...")
        } finally {
            lock.unlock()
        }
    }
}