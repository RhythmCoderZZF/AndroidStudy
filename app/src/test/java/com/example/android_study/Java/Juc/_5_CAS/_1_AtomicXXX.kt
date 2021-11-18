package com.example.android_study.Java.Juc._5_CAS

import org.junit.Test
import java.util.concurrent.atomic.*
import kotlin.concurrent.thread

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/6
 * Description:
 */
class _1_AtomicXXX {
    /**
     * Atomic [XXX] 原子整数
     */
    @Test
    fun t() {
        val count = AtomicInteger(3333)
        MutableList(30) {
            thread {
                while (true) {
                    val expect = count.get()
                    if (expect != 0) {
                        val update = expect - 3
                        count.compareAndSet(expect, update)
                    }
                }
            }
        }
        Thread.sleep(300)
        print(count.get())
    }

    /**
     * AtomicStampedReference  ABA原子引用
     */
    @Test
    fun t1() {
        val count = AtomicStampedReference(0, 0)
        thread {
            val expect = count.reference
            val stamp = count.stamp
            count.compareAndSet(expect, 1, stamp, stamp + 1)
        }
        thread {
            val expect = count.reference
            val stamp = count.stamp
            count.compareAndSet(expect, 0, stamp, stamp + 1)
        }

        val expect = count.reference
        val stamp = count.stamp
        Thread.sleep(1)
        print(count.compareAndSet(expect, 0, stamp, stamp + 1))
    }

    /**
     * Atomic [XXX] Array  原子数组
     */
    @Test
    fun t2() {
        val array = AtomicIntegerArray(1)
        array[0] = 3100000
        MutableList(80) {
            thread {
                while (true) {
                    Thread.yield()
                    val expect = array.get(0)
                    val update = array.get(0) - 31
                    if (expect == 0) {
                        break
                    }
                    array.compareAndSet(0, expect, update)
                }
            }
        }
        Thread.sleep(300)
        print(array)
    }

    /**
     *Atomic [XXX] FieldUpdater  字段更新器
     */
    @Test
    fun t3() {
        val updater = AtomicReferenceFieldUpdater.newUpdater(Student::class.java, String::class.java, "stu")
        val student = Student()
        thread {
            student.stu = "sdadf"
        }
        Thread.sleep(1)
        val b = updater.compareAndSet(student, "", "张三")
        print("$b  =>  $student")


    }

    inner class Student {
        @JvmField
        @Volatile
        var stu: String = ""


        override fun toString(): String {
            return "Student(stu='$stu')"
        }

    }

    @Test
    fun t4() {
        val longAdder = LongAdder()
        longAdder.add(170000)
        MutableList(30) {
            thread {
                while (true) {
                    if (longAdder.toLong() == 0L) {
                        break
                    }
                    longAdder.add(-17)
                }
            }
        }
        Thread.sleep(200)
        print(longAdder.sum())

    }

}