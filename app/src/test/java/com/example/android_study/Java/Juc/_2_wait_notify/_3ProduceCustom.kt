package com.example.android_study.Java.Juc._2_wait_notify

import com.example.android_study.Java.Juc._1_lock.notifyAll
import com.example.android_study.Java.Juc._1_lock.wait
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
            thread(name = "> 消费者 - $it") {
                while (true)
                    queue.custom()
            }
        }
        MutableList(4) {
            thread(name = "生产者 - $it") {
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
                while (queue.size == 0) {
                    println("${Thread.currentThread().name} 没有商品了")
                    queue.wait()
                }
                val m = queue.removeFirst()
                Thread.sleep(900)
                println("${Thread.currentThread().name} 剩余商品 -- ${queue.size}")
                queue.notifyAll()
                return m
            }
        }

        fun produce(message: Message) {
            Thread.sleep(1)
            synchronized(queue) {
                while (queue.size == 10) {
                    println("${Thread.currentThread().name} 商品已满")
                    queue.wait()
                }
                queue.add(message)
                Thread.sleep(900)
                println("${Thread.currentThread().name} 剩余商品--${queue.size}")
                queue.notifyAll()
            }
        }

        inner class Message {
            var o: Any? = null
        }
    }
}