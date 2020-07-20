package com.example.android_study._jetpack._aac_demo.logic.repository

import androidx.lifecycle.liveData
import com.example.android_study._jetpack._aac_demo.logic.model.bean.Banner
import com.example.android_study._jetpack._aac_demo.logic.network.NetWork
import kotlinx.coroutines.Dispatchers

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/17
 * Description:
 *
 * 1. 创建协程执行网络请求
 * 2. 请求结果用LiveData封装
 */
object Repository {

    /**
     * return : LiveData<Result<List<Banner>>>
     */
    fun getBanner() = liveData(Dispatchers.IO) {
        val result = try {
            val banner = NetWork.getBanner()
            if (banner.errorCode != 0) {
                Result.success(banner.data)
            } else {
                Result.failure(RuntimeException("response error:${banner.errorMsg}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Banner>>(e)
        }
        //Result<List<Banner>>
        emit(result)
    }

}
