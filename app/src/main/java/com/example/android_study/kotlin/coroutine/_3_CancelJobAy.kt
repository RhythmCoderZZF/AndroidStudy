package com.example.android_study.kotlin.coroutine

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.android_study.R
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity__2__coroutine_sample_ay.*
import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

/**
 * sample of cancel coroutine
 */
class _3_CancelJobAy : AppCompatActivity() {

    private lateinit var job: CompletableJob

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__2__coroutine_sample_ay)
        CmdUtil.showWindow()

        button.setOnClickListener {
            if (!::job.isInitialized) {
                initJob()
            }
            progressBar.startOrCancelJob(job)
        }
    }

    private fun ProgressBar.startOrCancelJob(job: Job) {
        if (progress > 0) {
            if (job.isActive || job.isCompleted) {
                CmdUtil.v("cancel job!")
                job.cancel(CancellationException("Resetting Job"))
            }
            initJob()
        } else {
            CmdUtil.v("start job...")
            //注意:也可以使用CoroutineScope返回的Job.cancel,但是会取消CoroutineScope作用域内所有的job
//            CoroutineScope(IO + job).launch {
//                repeat(100) {
//                    launch(Main) {
//                        progress = it
//                    }
//                }
//
//            }
            CoroutineScope(Dispatchers.Default + job).launch {
                for (i in 25..35) {
                    //注意!!!：cpu密集运算一定要在coroutine isActive状态，否则会造成内存泄漏
                    if (isActive) {
                        val f = fib(i)
                        CmdUtil.v("$f")
                        launch(Dispatchers.Main) { progressBar.progress += 10 }
                    }

                }
                CmdUtil.v("job complete!")
            }
            button.text = "cancel job"
        }
    }

    private fun initJob() {
        button.text = "start job"
        job = Job()
        //注意：成功完成或异常都会执行此回调，输出结果或异常信息
        job.invokeOnCompletion {
            it?.message.let {
                var msg = it
                if (msg.isNullOrBlank()) {
                    msg = "Unknown err"
                }
                CmdUtil.e("$job canceled by reason >>> $msg")

            }
        }
        progressBar.progress = 0
    }

    //斐波那契密集计算
    suspend fun fib(i: Int): Int {
        if (i == 0) return 0
        if (i == 1) return 1
        return fib(i - 1) + fib(i - 2)
    }
}