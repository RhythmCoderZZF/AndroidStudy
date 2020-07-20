package com.example.android_study._jetpack._lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.android_study.base.cmd.CmdUtil

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/28
 * Description:
 */
class MyObserver(var lifecycle: Lifecycle) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        CmdUtil.v("OnLifecycleEvent: ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        CmdUtil.v("OnLifecycleEvent: ON_START")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        CmdUtil.v("OnLifecycleEvent: ON_RESUME")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        CmdUtil.v("OnLifecycleEvent: ON_PAUSE")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        CmdUtil.v("OnLifecycleEvent: ON_STOP")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        CmdUtil.v("OnLifecycleEvent: ON_DESTROY")
    }

    fun getCurrLifeState() {
        CmdUtil.e("${lifecycle.currentState}")
    }
}