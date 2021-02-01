package com.example.android_study._base.net.net

import com.example.android_study._base.net.models.BaseResponse


/**
 * Author:create by RhythmCoder
 * Date:2020/8/19
 * Description:处理请求结果的包装类
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

fun <T> handleResult(response: BaseResponse<T>): Resource<BaseResponse<T>> {
    if (response.errorCode != 0) {
        return Resource.Error(null, response.errorMsg)
    }
    return Resource.Success(response)
}