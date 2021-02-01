package com.example.android_study.kotlin.flow.flow_livedata

import androidx.lifecycle.*
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach

/**
 * Author:create by RhythmCoder
 * Date:2020/11/27
 * Description:
 */
object VM {
    val observableInt = MutableLiveData<Int>()
            .asFlow().onEach {
                CmdUtil.e("haha $it")
            }
            .mapLatest {
                CmdUtil.e("haha $it")
                it
            }.buffer(0)
            .asLiveData()
}

