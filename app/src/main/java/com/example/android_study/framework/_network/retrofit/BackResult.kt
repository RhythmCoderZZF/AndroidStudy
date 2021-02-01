package com.example.android_study.framework._network.retrofit

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/13
 * Description:
 */
data class BackResult<T>(var data: T, var errorCode:Int, var errorMsg:String)