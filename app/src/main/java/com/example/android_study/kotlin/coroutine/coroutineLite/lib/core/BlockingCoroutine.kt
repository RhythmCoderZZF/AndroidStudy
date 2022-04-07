package com.bennyhuo.kotlin.coroutines.core

import com.bennyhuo.kotlin.coroutines.dispatcher.Dispatcher
import java.util.concurrent.LinkedBlockingDeque
import kotlin.coroutines.CoroutineContext

typealias EventTask = () -> Unit

//1.BlockingCoroutine的调度器。
class BlockingQueueDispatcher : LinkedBlockingDeque<EventTask>(), Dispatcher {

    override fun dispatch(block: EventTask) {

        //每一次调度都会将协程恢复的操作添加到阻塞队列中
        offer(block)
    }
}

class BlockingCoroutine<T>(context: CoroutineContext, private val eventQueue: LinkedBlockingDeque<EventTask>) : AbstractCoroutine<T>(context) {

    fun joinBlocking(): T {

        //2.当协程处于未完成状态时，不断循环取出阻塞队列中协程恢复的操作并执行，直到协程状态变为“完成”。注意该过程会阻塞当前线程
        while (!isCompleted) {
            eventQueue.take().invoke()
        }
        return (state.get() as CoroutineState.Complete<T>).let {
            it.value ?: throw it.exception!!
        }
    }
}