package com.example.android_study.java.juc._2_wait_notify

/**
 * 线程通信 -  保护性暂停 通知
 */
class _1GuardedObject {
//    @Test
//    fun t() {
//        val o = GuardedObject()
//        List(30) {
//            thread(name = ">$it<") {
//                o.get(1000)
//            }
//        }
//        thread {
//            Thread.sleep(200)
//            o.complete(1)
//        }
//        Thread.sleep(2000)
//    }
//
//    inner class GuardedObject {
//        @Volatile
//        private var response: Any? = null
//
//        fun get(time: Long): Any? {
//            val startTime = System.currentTimeMillis()
//            var passedTime: Long = 0
//            var waitTime: Long
//            synchronized(this) {
//                while (response == null) {
//                    waitTime = time - passedTime
//                    //1. 设置一个等待时间，防止死等
//                    if (waitTime <= 0) {
//                        break
//                    }
//                    try {
//                        println("${Thread.currentThread().name}  waiting response ...")
//                        wait(waitTime)
//                    } catch (e: InterruptedException) {
//                        println("${Thread.currentThread().name} InterruptedException!!")
//                    }
//                    passedTime = System.currentTimeMillis() - startTime
//                }
//                println("${Thread.currentThread().name} get response = $response")
//            }
//            //获取response
//            return response
//        }
//
//        fun complete(any: Any?) {
//            synchronized(this) {
//                //生产获取response
//                response = any
//                println("${Thread.currentThread().name} produce response ^^")
//                notifyAll()
//            }
//        }
//    }
    enum class A
}