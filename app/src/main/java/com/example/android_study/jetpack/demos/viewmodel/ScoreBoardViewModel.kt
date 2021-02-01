package com.example.android_study.jetpack.demos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/22
 * Description:
 */
const val KEY_NUMBER1 = "KEY_NUMBER1"
const val KEY_NUMBER2 = "KEY_NUMBER2"

class ScoreBoardViewModel(private val state: SavedStateHandle) : ViewModel() {
    private var lastA = 0
    private var lastB = 0

    private val _countA: MutableLiveData<Int>
        get() {
            //SavedStateHandle自动在系统杀死app时候自动保存MutableLiveData，第一次时没有需要手动添加一个MutableLiveData
            if (!state.contains(KEY_NUMBER1)) {
                state.set(KEY_NUMBER1, 0)
            }
            return state.getLiveData(KEY_NUMBER1)
        }


    private val _countB: MutableLiveData<Int>
        get() {
            if (!state.contains(KEY_NUMBER2)) {
                state.set(KEY_NUMBER2, 0)
            }
            return state.getLiveData(KEY_NUMBER2)
        }


    val countA get() = _countA
    val countB get() = _countB


    fun undo() {
        _countA.value = lastA
        _countB.value = lastB
    }

    fun reset() {
        lastA = _countA.value!!
        lastB = _countB.value!!
        _countA.value = 0
        _countB.value = 0
    }

    fun plusACount(i: Int) {
        lastA = _countA.value!!
        _countA.value = _countA.value!! + i
    }

    fun plusBCount(i: Int) {
        lastB = _countB.value!!
        _countB.value = _countB.value!! + i
    }

}