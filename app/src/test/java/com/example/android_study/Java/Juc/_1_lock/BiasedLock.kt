package com.example.android_study.Java.Juc._1_lock

import org.junit.Test
import org.openjdk.jol.info.ClassLayout
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/1
 * Description:
 *
 * 偏向锁 101
 * 001无偏向
 * 00轻量级锁
 */
class BiasedLock {
    /**
     * 启用偏向锁（请配置jvm配置参数）
     */
    @Test
    fun t() {
        //101
        println(ClassLayout.parseInstance(this).toPrintable())
    }

    /**
     * 1. 偏向锁撤销 - hashcode
     */
    @Test
    fun t1() {
        //101
        println(ClassLayout.parseInstance(this).toPrintable())
        hashCode()
        //001
        println(ClassLayout.parseInstance(this).toPrintable())
        synchronized(this) {
            //00
            println(ClassLayout.parseInstance(this).toPrintable())
        }
    }

    /**
     * 2. 偏向锁撤销 - 线程交替访问
     */
    @Test
    fun t2() {
        thread {
            //101
            println(ClassLayout.parseInstance(this).toPrintable())
            synchronized(this) {
                //101 + thread id
                println(ClassLayout.parseInstance(this).toPrintable())
            }
            //101 + thread id
            println(ClassLayout.parseInstance(this).toPrintable())
        }.join()
        synchronized(this) {
            //00 + lock record
            println(ClassLayout.parseInstance(this).toPrintable())
        }
        //01
        println(ClassLayout.parseInstance(this).toPrintable())
    }

    /**
     * 3. 偏向锁 - 批量重偏向
     */
    @Test
    fun t3() {
        val list = MutableList(30) {
            Any()
        }
        thread {
            for (i in list) {
                synchronized(i) {
                    println("${list.indexOf(i)} -- ${p(i)}")
                }
            }

            synchronized(list) {
                list.notify()
            }
        }
        thread {
            synchronized(list) {
                list.wait()
            }
            println("<<<<<<<<<<<<<<<<")
            for (i in list) {
                println("${list.indexOf(i)} -- ${p(i)}")
                synchronized(i) {
                    println("${list.indexOf(i)} -- ${p(i)}")
                }
                println("${list.indexOf(i)} -- ${p(i)}")
            }
        }.join()
        println(" -- ${p(Any())}")
    }

    /**
     * 偏向锁 - 批量撤销
     */
    @Test
    fun t4() {
        val lock = ReentrantLock()
        val newCondition = lock.newCondition()
        val newCondition1 = lock.newCondition()

        val list = MutableList(35) {
            Any()
        }

        thread {
            for (i in list) {
                println("${list.indexOf(i)} -- ${p(i)}")
                synchronized(i) {
                    println("${list.indexOf(i)} -- ${p(i)}")
                }
                println("${list.indexOf(i)} -- ${p(i)}")
            }
            lock.lock()
            newCondition.signal()
            lock.unlock()

        }
        thread {
            lock.lock()
            newCondition.await()
            println("<<<<<<<<222<<<<<<<<")
            for (i in list) {
                println("${list.indexOf(i)} -- ${p(i)}")
                synchronized(i) {
                    println("${list.indexOf(i)} -- ${p(i)}")
                }
                println("${list.indexOf(i)} -- ${p(i)}")

            }
            newCondition1.signal()
            lock.unlock()


        }

        thread {
            lock.lock()
            newCondition1.await()
            lock.unlock()
            println("<<<<<<<<333<<<<<<<<")
            for (i in list) {
                println("${list.indexOf(i)} -- ${p(i)}")
                synchronized(i) {
                    println("${list.indexOf(i)} -- ${p(i)}")
                }
                println("${list.indexOf(i)} -- ${p(i)}")
            }

        }.join()
        println(" -- ${p(Any())}")
    }

    @Test
    fun t5() {
        thread {
            synchronized(this) {
                println("thread wait()")
                wait()
                println("thread wake up!")
            }
        }
        Thread.sleep(20)
        synchronized(this) {
            notify()
            println("main sleep...")
            Thread.sleep(3000)
            println("main wake up!")

        }
    }


}

//查看对象头，前三位时锁标记
private fun p(i: Any): String {
    val s = ClassLayout.parseInstance(i).toPrintable()
    val split = s.split("(", limit = 3)
    val s1 = split[2]
    return s1.substring(0, 35)
}

fun Any.wait() = (this as java.lang.Object).wait()
fun Any.wait(long: Long) = (this as java.lang.Object).wait(long)
fun Any.notify() = (this as java.lang.Object).notify()
fun Any.notifyAll() = (this as java.lang.Object).notifyAll()