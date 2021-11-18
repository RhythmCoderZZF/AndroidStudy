package com.example.android_study.android.activity.fragment.lifecycle

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.activity.fragment.SampleFragment

/**
 * Author:create by RhythmCoder
 * Date:2021/7/22
 * Description:
 */
class LifeCycleSampleFragment : SampleFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun initialize() {
                CmdUtil.e("ON_CREATE")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun initialize1() {
                CmdUtil.e("ON_START")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun initialize2() {
                CmdUtil.e("ON_RESUME")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun initialize3() {
                CmdUtil.e("ON_PAUSE")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun initialize4() {
                CmdUtil.e("ON_STOP")
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun initialize5() {
                CmdUtil.e("ON_DESTROY")
            }
        })
    }
}