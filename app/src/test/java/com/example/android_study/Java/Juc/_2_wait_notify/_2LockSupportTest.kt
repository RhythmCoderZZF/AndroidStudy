package com.example.android_study.Java.Juc._2_wait_notify

import com.example.android_study.Java.Juc._1_lock.notify
import com.example.android_study.Java.Juc._1_lock.wait
import org.junit.Test
import java.util.concurrent.locks.LockSupport
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/3
 * Description:
 */
class _2LockSupportTest {
    @Test
    fun t() {
        val t = thread(name = "A") {
            println("A park...")
            LockSupport.park()
            println("A unPark...")
        }
        val t1 = thread(name = "B") {
            println("B park...")
            LockSupport.park()
            println("B unPark...")
        }
        Thread.sleep(1000)
        LockSupport.unpark(t1)

        LockSupport.park()
    }

    @Test
    fun t1() {
        val t = thread(name = "A") {
            println("A wait...")
            synchronized(this) {
                wait(100)
                println("A notify...")
            }
        }
        val t1 = thread(name = "B") {
            println("B wait...")
            synchronized(this) {
                wait(100)
                println("B notify...")
            }
        }
        Thread.sleep(10)
        synchronized(this) {
            notify()
        }
    }

    /**
     * 测试join——————————————————————————————————————————————————————————————
     */
    @Test
    fun t2() {
        val t1=thread {
            println("线程一执行...")
            Thread.sleep(1000)
            println("线程一执行完毕")
        }
        t1.join()
        println("主线程完毕")
    }

    @Test
    fun t2_1() {
        val t1=thread {
            println("线程一执行...")
            Thread.sleep(1000)
            println("线程一执行完毕")
        }
        try {
            t1.join()
        } catch (e: InterruptedException) {
            println("主线程中断执行")
        }
        println("主线程完毕")
    }
}