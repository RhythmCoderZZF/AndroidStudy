package com.example.android_study.jetpack.demo_wanandroid.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Author:create by RhythmCoder
 * Date:2020/8/19
 * Description:
 */
class HomeViewModelFactory (val homeRepository: HomeRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(homeRepository) as T
    }
}