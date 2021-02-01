package com.example.android_study.Data_Structure_and_Algorithms._1_sort

import org.junit.Test

/**
 * Author: create by RhythmCoder
 * Date: 2020/8/9
 * Description: 冒泡排序
 */
class BubbleSortTest {
    @Test
    fun t() {
        val util = BubbleSortUtil()
        val list= arrayOf(8,3,2,1,5)
//        util.sort(list)

    }

    /**
     * 冒泡排序工具类
     */
    private inner class BubbleSortUtil() {

        fun sort(list: Array<Comparable<Any>>) {
            for (i in list.size - 1..0) {
                for (j in 0..i) {
                    if (greater(list[j], list[j + 1])) {
                        exchange(list, j, j + 1)
                    }
                }
            }
        }
        private fun greater(comparable: Comparable<Any>, comparable1: Comparable<Any>) = comparable > comparable1

        private fun exchange(list: Array<Comparable<Any>>, i: Int, j: Int) {
            var temp = list[i]
            list[j] = list[i]
            list[i] = temp
        }
    }
}