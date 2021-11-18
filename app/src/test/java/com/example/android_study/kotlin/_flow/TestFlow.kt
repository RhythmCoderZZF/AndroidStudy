package com.example.android_study.kotlin._flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2021/10/27
 * Description:
 */
class TestFlow {

    @Test
    fun test_collect() = runBlocking {
        val f = flow {
            var i = 0
            while (true) {
                delay(500)
                emit(i++)
            }
        }
        launch {
            f.collect {
                println("> 1:$it")
            }
        }
        delay(2000)
        launch {
            f.collect {
                println("> 2:$it")
            }
        }
        return@runBlocking
    }
}