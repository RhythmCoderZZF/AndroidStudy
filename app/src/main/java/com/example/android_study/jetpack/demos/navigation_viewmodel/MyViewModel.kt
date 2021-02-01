package com.example.android_study.jetpack.demos.navigation_viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/24
 * Description:
 */
class MyViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count = _count

    fun minus() {
        _count.value = _count.value?.minus(1)
    }
}