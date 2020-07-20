package com.example.android_study._jetpack._aac_demo.logic.model

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/17
 * Description:
 */
data class BackResult<T>(val data: T, val errorCode: Int, val errorMsg: String)