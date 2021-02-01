package com.example.android_study.jetpack.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/12
 * Description:
 */
class MainViewModelFactory(val count: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(count) as T
    }
}