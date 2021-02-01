package com.example.android_study.java.juc._2_wait_notify

import com.example.android_study.java.juc._1_lock.notifyAll
import com.example.android_study.java.juc._1_lock.wait
import org.junit.Test
import java.util.concurrent.locks.LockSupport
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/4
 * Description:
 *
 * 多线程同步交替执行的sample实现，Thread-0打印a Thread-1打印b Thread-2打印c
 */
class _4ThreadNotification {
    @Volatile
    var flag = false

    @Test
    fun testWaitNotify() {
        val w = WaitNotify()
        val start = System.currentTimeMillis()
        thread {
            w.print(0, 1)
        }
        thread {
            w.print(1, 2)
        }
        thread {
            w.print(2, 0)
        }.join()
        val end = System.currentTimeMillis()
        println(">> ${end - start}")//4550
    }

    private inner class WaitNotify(val loop: Int = 200000) {
        private var flag = 0

        fun print(f: Int, nextFlag: Int) {
            repeat(loop) {
                synchronized(this) {
                    while (flag != f) {
                        try {
                            wait()
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    flag = nextFlag
                    notifyAll()
                }
            }
        }
    }

    @Test
    fun testReentrantLock() {
        val w1 = AwaitSignal()
        val start = System.currentTimeMillis()
        thread {
            w1.print(0, 1)
        }
        thread {
            w1.print(1, 2)
        }
        thread {
            w1.print(2, 0)
        }.join()
        val end = System.currentTimeMillis()
        println(">> ${end - start}")//3700
    }

    private inner class AwaitSignal(val loop: Int = 200000) : ReentrantLock() {
        private val c0 = newCondition()
        private val c1 = newCondition()
        private val c2 = newCondition()
        private var flag = 0

        fun print(f: Int, nextFlag: Int) {
            repeat(loop) {
                lock()
                try {
                    while (flag != f) {
                        when (f) {
                            0 -> c0.await()
                            1 -> c1.await()
                            2 -> c2.await()
                        }
                    }
                    flag = nextFlag
                    when (flag) {
                        0 -> c0.signal()
                        1 -> c1.signal()
                        2 -> c2.signal()
                    }
                } finally {
                    unlock()
                }
            }
        }
    }

    @Test
    fun testParkUnPark() {
        val w1 = ParkUnPark()
        val start = System.currentTimeMillis()
        lateinit var t0: Thread
        lateinit var t1: Thread
        lateinit var t2: Thread

        t2 = thread(start = false) {
            w1.print(t0)
        }
        t1 = thread(start = false) {
            w1.print(t2)
        }
        t0 = thread(start = false) {
            w1.print(t1)
        }
        t0.start()
        t1.start()
        t2.start()

        LockSupport.unpark(t0)
        t2.join()

        val end = System.currentTimeMillis()
        println(">> ${end - start}")//3550
    }

    private inner class ParkUnPark {
        fun print(next: Thread) {
            repeat(200000) {
                LockSupport.park()
                LockSupport.unpark(next)
            }
        }
    }

}