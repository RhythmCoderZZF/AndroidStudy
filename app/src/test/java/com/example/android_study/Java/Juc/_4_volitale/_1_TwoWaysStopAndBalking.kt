package com.example.android_study.Java.Juc._4_volitale

import org.junit.Test
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/6
 * Description: 两阶段终止与balking（犹豫）模式
 */
class _1_TwoWaysStopAndBalking {

    @Test
    fun main() {
        val service = Service()
        MutableList(30) {
            thread {
                service.stop()
            }
        }
        MutableList(30) {
            thread {
                service.start()
            }
        }
        Thread.sleep(2000)
        MutableList(30) {
            thread {
                service.stop()
            }
        }

        Thread.sleep(2000)
        println("main end")
    }

    private inner class Service {
        @Volatile
        private var stop = false

        @Volatile
        private var started = false

        @Volatile
        private lateinit var thread: Thread

        fun start() {
            if (!started) {
                synchronized(this) {
                    if (!started) {
                        started = true
                        stop = false
                        thread = thread(name = "A") {
                            while (!stop) {
                                try {
                                    Thread.sleep(10)
                                    println("${thread.name} - 监控数据...")
                                } catch (e: InterruptedException) {
                                    Thread.currentThread().interrupt()
                                }
                            }
                            println("结束监控")
                        }
                    }
                }
            }
        }

        fun stop() {
            if (started) {
                synchronized(this) {
                    if (started) {
                        stop = true
                        started = false
                        thread.interrupt()
                    }
                }
            }
        }
    }


}
