package com.example.android_study._jetpack._viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android_study.base.cmd.CmdUtil

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/12
 * Description:
 */
class MainViewModel(var counter: Int = 0) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        CmdUtil.e("onCleared")
    }
}