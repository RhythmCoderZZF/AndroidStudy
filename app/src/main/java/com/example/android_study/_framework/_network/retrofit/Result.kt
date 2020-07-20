package com.example.android_study._framework._network.retrofit

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/13
 * Description:
 */
data class Result<T>(var data: T, var errorCode:Int, var errorMsg:String)