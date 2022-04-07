package com.example.android_study.kotlin._coroutine

import kotlinx.coroutines.*
import org.junit.Test
import java.io.FileReader

class T {
    @Test
    fun cancel() = runBlocking {
        val job = launch(start = CoroutineStart.ATOMIC) {
            val br = FileReader("C:\\Users\\lenovo\\Desktop\\2_TODO.txt").buffered()
            var line: String?
            try {
                ensureActive()
                while (br.readLine().also { line = it } != null && isActive) {
                    println(line)
                    if (isActive) {
                        cancel()
                    }
                }
            } catch (e: Exception) {
            } finally {
                br.close()
            }
        }
        job.join()
    }

}
