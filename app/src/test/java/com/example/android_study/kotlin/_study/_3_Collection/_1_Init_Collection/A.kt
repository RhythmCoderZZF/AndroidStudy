package com.example.android_study.kotlin._study._3_Collection._1_Init_Collection

import java.util.*

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/8
 * Description：构造集合
 *
 * https://www.kotlincn.net/docs/reference/constructing-collections.html
 */

class A {
    init {
        //List
        listOf(1, 2)                    //List  默认：ArrayList  检出元素
        mutableListOf(1)                //MutableList   默认：ArrayList  添加删除元素
        arrayListOf(1, 2)               //ArrayList


        //Set
        setOf<Int>()                     //Set   默认：LinkedHashSet    检出元素
        mutableSetOf<Int>()              //MutableSet    默认：LinkedHashSet    添加删除元素
        hashSetOf<Int>()                 //HashSet
        linkedSetOf<Int>()               //LinkedHashSet
        sortedSetOf<Int>()               //TreeSet

        //Map
        mapOf(1 to "a", 2 to "b")                   //Map   默认：LinkedHashMap        检出元素
        mutableMapOf(1 to "a", 2 to "b")            //MutableMap    默认：LinkedHashMap        添加删除元素
        hashMapOf(1 to "a", 2 to "b")               //HashMap
        linkedMapOf(1 to "a", 2 to "b")             //LinkedHashMap
        sortedMapOf(1 to "a", 2 to "b")             //TreeMap

        val doubled = List(3, { it * 2 })  // 如果你想操作这个集合，应使用 MutableList
        println(doubled)
        val linkedList = LinkedList<String>(listOf("one", "two", "three"))
        val presizedSet = HashSet<Int>(32)
    }
}