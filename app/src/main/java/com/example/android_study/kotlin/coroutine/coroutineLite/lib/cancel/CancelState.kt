package com.bennyhuo.kotlin.coroutines.cancel

import com.bennyhuo.kotlin.coroutines.OnCancel

sealed class CancelState {
    override fun toString(): String {
        return "CancelState.${this.javaClass.simpleName}"
    }
    //状态1：运行
    object InComplete : CancelState()
    //状态2：运行且拥有一个注册了的取消监听器
    class CancelHandler(val onCancel: OnCancel): CancelState()
    //状态3：完成
    class Complete<T>(val value: T? = null, val exception: Throwable? = null) : CancelState()
    //状态4：被取消的完成状态
    object Cancelled : CancelState()
}