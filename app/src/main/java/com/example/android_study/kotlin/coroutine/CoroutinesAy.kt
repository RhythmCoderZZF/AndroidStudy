package com.example.android_study.kotlin.coroutine

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import kotlinx.android.synthetic.main.activity_coroutines_ay.*

class CoroutinesAy : BaseActivity() {
    val list = listOf(
            Entry("1. 基础", _1_BaseAy::class.java),
            Entry("2. 超时", _2_TimeOutAy::class.java),
            Entry("3. 取消", _3_CancelJobAy::class.java),
            Entry("4. async await", _4_AsyncAndAwaitAy::class.java),
            Entry("5. 异常处理", _5_DealWithException::class.java)
    )
    override fun getLayoutId() = R.layout.activity_coroutines_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv,list)
    }


}

