package com.bennyhuo.kotlin.coroutines.core

sealed class RecursiveList<out T> {
    object Nil: RecursiveList<Nothing>()
    class Cons<T>(val head: T, val tail: RecursiveList<T>): RecursiveList<T>()
}

//1.移除指定的element
fun <T> RecursiveList<T>.remove(element: T): RecursiveList<T> {
    return when(this){
        RecursiveList.Nil -> this
        is RecursiveList.Cons -> {
            if(head == element){
                return tail
            } else {
                RecursiveList.Cons(head, tail.remove(element))
            }
        }
    }
}

tailrec fun <T> RecursiveList<T>.forEach(action: (T) -> Unit): Unit = when(this){
    RecursiveList.Nil ->Unit
    is RecursiveList.Cons -> {
        action(this.head)
        this.tail.forEach(action)
    }
}

//2.遍历element。找到匹配的element并执行响应逻辑
inline fun <reified T> RecursiveList<Any>.loopOn(crossinline action: (T) -> Unit) = forEach {
    when(it){
        is T -> action(it)
    }
}