package com.example.android_study.java.juc._2_wait_notify

import com.example.android_study.java.juc._1_lock.notifyAll
import com.example.android_study.java.juc._1_lock.wait
import org.junit.Test
import java.util.*
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/3
 * Description:
 *
 * 生产消费模型
 */
class _3ProduceCustom {
    @Test
    fun t() {
        val queue = MessageQueue()
        MutableList(2) {
            thread(name = "c.txt - $it") {
                while (true)
                    queue.custom()
            }
        }
        MutableList(4) {
            thread(name = "p - $it") {
                while (true)
                    queue.produce(queue.Message())
            }
        }
        synchronized(this) { wait() }
    }

    inner class MessageQueue {
        private val queue = LinkedList<Message>()

        fun custom(): Message {
            Thread.sleep(1)
            synchronized(queue) {
                Thread.sleep(900)
                while (queue.size == 0) {
                    println("${Thread.currentThread().name} custom -> wait")
                    queue.wait()
                }
                queue.notifyAll()
                val m = queue.removeFirst()
                println("${Thread.currentThread().name} custom -- ${queue.size}")
                return m
            }
        }

        fun produce(message: Message) {
            Thread.sleep(1)
            synchronized(queue) {
                Thread.sleep(900)
                while (queue.size == 10) {
                    println("${Thread.currentThread().name} produce <- wait")
                    queue.wait()
                }
                queue.notifyAll()
                queue.add(message)
                println("${Thread.currentThread().name} produce ++ ${queue.size}")
            }
        }

        inner class Message() {
            var o: Any? = null
        }
    }
}