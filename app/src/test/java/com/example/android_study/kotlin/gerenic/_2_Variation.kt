package com.example.android_study.kotlin.gerenic

import com.example.android_study.R
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/26
 * Description:泛型的型变
 */
class _2_Variation {

    /**
     * “声明处协变”
     */
    @Test
    fun main() {
        val eduBookStore: BookStore<EduBook> = BookStore()
        val bookStore: BookStore<Book> = eduBookStore

        val book: Book = bookStore.getBook()
        val eduBook: EduBook = eduBookStore.getBook()
//        val book1: EduBook = bookStore.getBook() ❌

        //星投影
        val map: Map<*, *> = mapOf<String, Int>()
        map[1]
    }

    private interface Book
    private interface EduBook : Book

    private class BookStore<out T : Book> {
        fun getBook(): T {
            TODO()
        }
    }


    /**
     * "声明处逆变“
     */
    @Test
    fun main1() {
        val dustbin: Dustbin<Waste> = Dustbin()
        val dryDustbin: Dustbin<DryWaste> = dustbin

        val waste = Waste()
        val dryWaste = DryWaste()

        dustbin.put(waste)
        dustbin.put(dryWaste)

        dryDustbin.put(dryWaste)

        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { "" }
        copy(ints, any)
    }

    private open class Waste
    private class DryWaste : Waste()

    private class Dustbin<in T : Waste> {
        fun put(waste: T) {}
    }

    /**
     * “使用处协变”
     */
    @Test
    fun main2() {
        val ints: Array<Int> = arrayOf(1, 2, 3)
        val any = Array<Any>(3) { "" }
        copy(ints, any)
    }

    fun copy(from: Array<out Any>, to: Array<Any>) {
        assert(from.size == to.size)
        for (i in from.indices)
            to[i] = from[i]
    }

    @Test
    fun main3() {
        val array: Array<CharSequence> = arrayOf("a")
        insert(array, "b")
    }

    fun insert(dest: Array<in String>, value: String) {
        if (dest.isNotEmpty()) {
            dest[dest.size - 1] = value
        }
    }
}