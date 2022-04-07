package com.bennyhuo.kotlin.coroutines.cancel

/**
 * 描述挂起函数（也可以理解为协程）当前挂起的状态。
 */
enum class CancelDecision {

    //“初始”、“挂起”、“恢复”
    UNDECIDED, SUSPENDED, RESUMED
}