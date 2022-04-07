package com.bennyhuo.kotlin.coroutines.dispatcher

import com.bennyhuo.kotlin.coroutines.dispatcher.ui.AndroidDispatcher

object Dispatchers {

    val Android by lazy {
        DispatcherContext(AndroidDispatcher)
    }

    val Default by lazy {
        DispatcherContext(DefaultDispatcher)
    }
}