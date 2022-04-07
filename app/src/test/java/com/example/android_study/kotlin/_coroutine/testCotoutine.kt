package com.example.android_study.kotlin._coroutine

import com.bennyhuo.kotlin.coroutines.utils.log
import kotlinx.coroutines.*
import org.junit.Test

class testCotoutine {
    @Test
    fun coroutine() = runBlocking {
        val handler = CoroutineExceptionHandler { _, t ->
            log("exception:${t.message}")
        }
        launch(handler) {
            launch {
                delay(1000)
                log("child")
            }
            delay(100)
            log("parent")
            throw RuntimeException("err!")
        }.join()
    }

}
