package com.example.android_study.kotlin._coroutine

import com.bennyhuo.kotlin.coroutines.delay
import com.bennyhuo.kotlin.coroutines.exception.CoroutineExceptionHandler
import com.bennyhuo.kotlin.coroutines.launch
import com.bennyhuo.kotlin.coroutines.runBlocking
import com.bennyhuo.kotlin.coroutines.utils.log
import org.junit.Test

class testCotoutineLite {
    @Test
    fun coroutineLite() = runBlocking {
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
