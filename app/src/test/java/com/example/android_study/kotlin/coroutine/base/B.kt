package com.example.android_study.kotlin.coroutine.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test
import kotlin.coroutines.suspendCoroutine

/**
 * Author:create by RhythmCoder
 * Date:2020/8/31
 * Description:
 */
class B {
    private suspend fun getResult() = suspendCoroutine<String> {
        it.resumeWith(Result.success("OK!"))
    }

    @Test
    fun m() {
        GlobalScope.launch(Dispatchers.Main) {
            print("111")
            withContext(IO){
                print("222 ${getResult()}")
            }
        }
    }
}