package com.example.android_study.jetpack.databinding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/21
 * Description:
 */
class DataBindingViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count = _count
    fun add() {
        _count.value = _count.value?.plus(1)
    }

}