package com.example.android_study.kotlin.coroutine.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import kotlin.coroutines.suspendCoroutine

/**
 * Author:create by RhythmCoder
 * Date:2020/8/24
 * Description:
 */
class A {
    private suspend fun getResult() = suspendCoroutine<String> {
        it.resumeWith(Result.success("OK!"))
    }

    @Test
    fun m() {
        GlobalScope.launch(Dispatchers.IO) {
            print("@@@@@@@@ ${getResult()}")
        }
    }
}

