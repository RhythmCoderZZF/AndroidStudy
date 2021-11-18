package com.example.android_study.kotlin.channel.base

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_channel_base.*
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class KotlinChannelBaseActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_channel_base

    @ExperimentalCoroutinesApi
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        /* —————————————————————————————————————《基础》—————————————————————————————————*/
        btn_1.setOnClickListener {
            val channel = Channel<Int>()
            launch {
                channel.send(1)
            }
            launch {
                delay(1000)
                val i = channel.receive()
                CmdUtil.i("receive:$i")
            }
        }

        /* —————————————————————————————————————《迭代》—————————————————————————————————*/
        btn_iterator.setOnClickListener {
            val channel = Channel<Int>()
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                }
            }
            launch {
                /* 三种方式 */
//                val iterator = channel.iterator()
//                while (iterator.hasNext()) {
//                    val i = iterator.next()
//                    delay(1000)
//                    CmdUtil.i("receive:$i")
//                }

                for (i in channel) {
                    delay(1000)
                    CmdUtil.i("receive:$i")
                }
            }
        }

        /* —————————————————————————————————————《produce & actor》—————————————————————————————————*/
        btn_produce.setOnClickListener {

            //创建一个
            val channel = produce {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    send(it)
                }
            }

            launch {
                channel.consumeEach {
                    delay(1000)
                    CmdUtil.i("receive:$it")
                }
            }
        }

        btn_actor.setOnClickListener {
            val channel = lifecycleScope.actor<Int> {
                consumeEach {
                    delay(1000)
                    CmdUtil.i("receive:$it")
                }
            }
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                }
            }
        }


        /* —————————————————————————————————————《缓存容量》—————————————————————————————————*/
        btn_capacity.setOnClickListener {
            val channel = Channel<Int>(5)
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                }
            }
            launch {
                for (i in channel) {
                    delay(1000)
                    CmdUtil.i("receive:$i")
                }
            }
        }

        /* —————————————————————————————————————《关闭Channel》—————————————————————————————————*/
        btn_close.setOnClickListener {
            val channel = Channel<Int>(5)
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                    if (it == 3) {
                        channel.close()
                        CmdUtil.e("isClosedSend:${channel.isClosedForSend}|isClosedReceive:${channel.isClosedForReceive}")
                        return@launch
                    }
                }
            }
            launch {
                for (i in channel) {
                    delay(1000)
                    CmdUtil.i("receive:$i")
                }
                CmdUtil.e("isClosedSend:${channel.isClosedForSend}|isClosedReceive:${channel.isClosedForReceive}")
            }
        }
        /* —————————————————————————————————————《BroadcastChannel》—————————————————————————————————*/
        //普通Channel，只能一对一
        btn_normal.setOnClickListener {
            val channel = Channel<Int>(5)
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                }
            }
            repeat(3){
                launch {
                    for (i in channel) {
                        delay(1000)
                        CmdUtil.i("$it-receive:$i")
                    }
                }
            }
        }
        //BroadcastChannel，能进行一对多
        btn_broadcast.setOnClickListener {
            val channel = BroadcastChannel<Int>(Channel.BUFFERED)
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                }
            }
            repeat(3){
                launch {
                    val ch=channel.openSubscription()
                    for (i in ch) {
                        delay(1000)
                        CmdUtil.i("$it-receive:$i")
                    }
                }
            }
        }
    }
}