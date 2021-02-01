package com.example.android_study.kotlin.coroutine

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity__1__base_ay.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class _1_BaseAy : BaseActivity() {
    override fun getLayoutId() = R.layout.activity__1__base_ay
    override fun initViewAndData(savedInstanceState: Bundle?) {
        /* CoroutineScope lifecycleScope */
        button.setOnClickListener {
            textView.text = ""

            CoroutineScope(Main).launch {
                delay(4000)
                textView.append("1 CoroutineScope(Main):${Thread.currentThread().name}\n")
            }
            lifecycleScope.launchWhenCreated {
                delay(500)
                textView.append("2 lifecycleScope:${Thread.currentThread().name}\n")
            }
            CoroutineScope(IO).launch {
                delay(1500)
                val name = Thread.currentThread().name
                textView.post {
                    textView.append("3 delay(IO):$name\n")
                }

            }
            CoroutineScope(IO).launch {
                Thread.sleep(1500)
                val name = Thread.currentThread().name
                textView.post {
                    textView.append("3 sleep(IO):$name\n")
                }
            }

        }
        /* launch */
        button1.setOnClickListener {
            textView1.text = ""
            CoroutineScope(Main).launch {
                textView1.append("111 launch-start:${Thread.currentThread().name}\n")
                launch(IO) {
                    delay(500)
                    val name1 = Thread.currentThread().name
                    textView1.post {
                        textView1.append("1 >> $name1\n")
                    }
                }
                textView1.append("222 launch-middle:${Thread.currentThread().name}\n")
                launch(IO)  {
                    delay(1000)
                    val name1 = Thread.currentThread().name
                    textView1.post {
                        textView1.append("2 >> $name1\n")
                    }
                }
                textView1.append("333 launch-end:${Thread.currentThread().name}\n")
            }
        }

        /* withContext */
        button2.setOnClickListener {
            textView2.text = ""
            CoroutineScope(Main).launch {
                textView2.append("111 withContext-start:${Thread.currentThread().name}\n")
                val res = withContext(IO) {
                    delay(500)
                    Thread.currentThread().name
                }
                textView2.append("222 withContext-middle:${Thread.currentThread().name}\n")
                textView2.append("1 >> $res \n")
                val res1 = withContext(IO) {
                    delay(1000)
                    Thread.currentThread().name
                }
                textView2.append("2 >> $res1 \n")
                textView2.append("333 withContext-end:${Thread.currentThread().name}\n")
            }
        }

        /* async_await */
        button3.setOnClickListener {
            textView3.text = ""
            CoroutineScope(Main).launch {
                val deferred = async(IO) {
                    delay(500)
                    Thread.currentThread().name
                }
                textView3.append(deferred.await() + "\n")
                val deferred1 = async(IO) {
                    delay(1000)
                    Thread.currentThread().name
                }
                textView3.append(deferred1.await() + "\n")
            }
        }
    }
}