package com.example.android_study.kotlin.channel

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_kotlin_channel.*
import kotlinx.android.synthetic.main.activity_rv.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ObsoleteCoroutinesApi
class KotlinChannelMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_kotlin_channel

    @ExperimentalCoroutinesApi
    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()

        /* —————————————————————————————————————《基础》—————————————————————————————————*/
        btn_1.setOnClickListener {
            val channel = Channel<Int>()
            launch {
                CmdUtil.v("sending")
                channel.send(1)
                CmdUtil.v("sent:1")
            }
            launch {
                delay(1000)
                CmdUtil.i("receiving")
                val i = channel.receive()
                CmdUtil.i("received:$i")
            }
        }

        /* —————————————————————————————————————《迭代》—————————————————————————————————*/
        btn_iterator.setOnClickListener {
            val channel = Channel<Int>()
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                    delay(500)
                }
            }
            launch {
//                val iterator = channel.iterator()
//                while (iterator.hasNext()) {
//                    val i = iterator.next()
//                    delay(1000)
//                    CmdUtil.i("receive:$i")
//                }

                for (i in channel) {
                    CmdUtil.i("receive:$i")
                }
            }
        }

        /* —————————————————————————————————————《分类》—————————————————————————————————*/
        btn_rendezvous.setOnClickListener {
            val channel = Channel<Int>(capacity = Channel.RENDEZVOUS)
            launch {
                repeat(5) {
                    channel.send(it)
                    CmdUtil.i("sent:$it")
                }
                channel.close()
            }
            launch {
                while (!channel.isClosedForReceive) {
                    val value = channel.receiveOrNull()
                    CmdUtil.v("receive:$value")
                    delay(1000)
                }
            }
        }

        btn_unlimited.setOnClickListener {
            val channel = Channel<Int>(capacity = Channel.UNLIMITED)
            launch {
                repeat(5) {
                    channel.send(it)
                    CmdUtil.i("sent:$it")
                }
                channel.close()
            }
            launch {
                while (!channel.isClosedForReceive) {
                    val value = channel.receiveOrNull()
                    CmdUtil.v("receive:$value")
                    delay(1000)
                }
            }
        }

        btn_conflated.setOnClickListener {
            val channel = Channel<Int>(capacity = Channel.CONFLATED)
            launch {
                repeat(5) {
                    channel.send(it)
                    CmdUtil.i("sent:$it")
                }
                channel.close()
            }
            launch {
                while (!channel.isClosedForReceive) {
                    val value = channel.receiveOrNull()
                    CmdUtil.v("receive:$value")
                    delay(1000)
                }
            }
        }

        btn_buffered.setOnClickListener {
            val channel = Channel<Int>(capacity = Channel.BUFFERED)
            launch {
                repeat(5) {
                    channel.send(it)
                    CmdUtil.i("sent:$it")
                }
                channel.close()
            }
            launch {
                while (!channel.isClosedForReceive) {
                    val value = channel.receiveOrNull()
                    CmdUtil.v("receive:$value")
                    delay(1000)
                }
            }
        }

        btn_fixed.setOnClickListener {
            val channel = Channel<Int>(3)
            launch {
                repeat(5) {
                    channel.send(it)
                    CmdUtil.i("sent:$it")
                }
                channel.close()
            }
            launch {
                while (!channel.isClosedForReceive) {
                    val value = channel.receiveOrNull()
                    CmdUtil.v("receive:$value")
                    delay(1000)
                }
            }
        }


        /* —————————————————————————————————————《关闭Channel》—————————————————————————————————*/
        btn_close.setOnClickListener {
            val channel = Channel<Int>(Channel.BUFFERED)
            launch {
                launch {
                    delay(500)
                    channel.close()
                }
                while (!channel.isClosedForSend) {
                    channel.send(1)
                    CmdUtil.v("send:1")
                    delay(100)
                }
            }
            launch {
                while (!channel.isClosedForReceive) {
                    CmdUtil.i("receive:${channel.receiveOrNull()}")
                    delay(1000)
                }
            }
        }


        /* —————————————————————————————————————《produce & actor》—————————————————————————————————*/
        btn_produce.setOnClickListener {
            val receiveChannel = produce(capacity = Channel.RENDEZVOUS) {
                while (!isClosedForSend) {
                    CmdUtil.v("send:1")
                    send(1)
                    delay(500)
                }
            }
            launch {
                receiveChannel.consumeEach {
                    CmdUtil.i("receive:$it")
                }
            }
        }

        btn_actor.setOnClickListener {
            val sendChannel = actor<Int>(capacity = Channel.UNLIMITED) {
                consumeEach {
                    CmdUtil.i("receive:$it")
                }
            }
            launch {
                while (!sendChannel.isClosedForSend) {
                    CmdUtil.v("send:1")
                    sendChannel.send(2)
                    delay(500)
                }
            }
        }

        /* —————————————————————————————————————《BroadcastChannel》—————————————————————————————————*/
        //普通Channel，只能一对一
        btn_normal.setOnClickListener {
            val channel = Channel<Int>()
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                    delay(500)
                }
            }
            repeat(3) {
                val coroutineName = CoroutineName("coroutine-$it")
                launch(coroutineName) {
                    for (i in channel) {
                        CmdUtil.i("${coroutineContext[CoroutineName]} receive:$i")
                    }
                }
            }
        }
        //BroadcastChannel，能进行一对多
        btn_broadcast.setOnClickListener {
            val channel = BroadcastChannel<Int>(Channel.BUFFERED)
            repeat(3) {
                val coroutineName = CoroutineName("coroutine-$it")
                launch(coroutineName) {
                    val ch = channel.openSubscription()
                    for (i in ch) {
                        CmdUtil.i("${coroutineContext[CoroutineName]} receive:$i")
                    }
                }
            }
            launch {
                repeat(5) {
                    CmdUtil.v("send:$it")
                    channel.send(it)
                    delay(500)
                }
            }
        }
    }
}