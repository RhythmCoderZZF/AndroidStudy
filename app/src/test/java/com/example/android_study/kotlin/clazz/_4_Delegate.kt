package com.example.android_study.kotlin.clazz

import org.junit.Test

/**
 * Author:create by RhythmCoder
 * Date:2022/2/22
 * Description:
 * 1.接口代理
 * 2.属性代理
 */
class _4_Delegate {

    /**
     * 1.接口代理
     */
    @Test
    fun main() {
        val api = ApiWrapper(ApiImpl())
        api.getResponse()
    }

    private interface Api {
        fun getResponse()
        fun sendRequest()
    }

    private class ApiImpl : Api {
        override fun getResponse() {
            println("getResponse")
        }

        override fun sendRequest() {
            println("sendRequest")
        }
    }

    //api
    private class ApiWrapper(var api: Api) : Api by api {
        override fun getResponse() {
            api.getResponse()
            println("log response...")
        }
    }

    /**
     * 属性代理
     */
    @Test
    fun main1() {
        //1.by lazy
        val d = D()
        println(d.y)
    }

    private class D {
        var x = 0
        val y by lazy {
            x % 2 == 0
        }
    }
}