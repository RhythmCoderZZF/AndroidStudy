package com.example.android_study._kotlin._study._3_Collection._4_Collection_Operation

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/9
 * Description:转换
 */
val l = listOf(1, 2, 3)

private data class StringContainer(val values: List<String>)

fun main() {
    println("~~~~~~~~~~~~~map 转换~~~~~~~~~~~~~~~")

    //1

    println(l.map { if (it == 2) null else it + 1 })
    println(l.mapIndexed { idx, value -> if (idx == 0) null else value + idx })

    //1.1 过滤null
    println(l.mapNotNull { if (it == 2) null else it + 1 })
    println(l.mapIndexedNotNull { idx, value -> if (idx == 0) null else value + idx })

    //1.2 Map键值转换
    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    println(numbersMap.mapKeys { it.key.toUpperCase() })
    println(numbersMap.mapValues { it.value + 1 })

    println("~~~~~~~~~~~~~zip 压缩~~~~~~~~~~~~~~~")


    //2 默认压缩为一个Pair(K,V)
    val l1 = listOf("one", "two", "three")
    println(l1 zip l)
    println(l1.zip(l))

    //2.1 指定压缩结果
    val z = l.zip(l1) { i, k -> "$i -> $k" }
    println(z)

    //2.2 反向解压
    println(l1.zip(l).unzip())


    println("~~~~~~~~~~~~~~associate 关联~~~~~~~~~~~~~~")

    //3 转换允许从集合元素和与其关联的某些值构建 Map。 在不同的关联类型中，元素可以是关联 Map 中的键或值。
    println(l.associateWith { it + 1 })

    //3.1 结果为map键值对,返回值为value
    println(l.associateBy { it + 1 })
    //3.2 返回自定义Pair
    println(l.associateBy({ it }, { it }))


    println("~~~~~~~~~~~~~~flat 打平~~~~~~~~~~~~~~")


    //4 操作嵌套的集合，则可能会发现提供对嵌套集合元素进行打平访问的标准库函数很有用。
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten())

    //4.1 flatMap
    val containers = listOf(
            StringContainer(listOf("one", "two", "three")),
            StringContainer(listOf("four", "five", "six")),
            StringContainer(listOf("seven", "eight"))
    )
    println(containers.flatMap { it.values })

    println("~~~~~~~~~~~~~~joinToString 集合转为字符串表示~~~~~~~~~~~~~~")

    println(l.joinToString())
    println(l.joinToString(separator = " | ", transform = { "hi $it" }))
}

