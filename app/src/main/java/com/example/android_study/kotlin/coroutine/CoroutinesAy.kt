package com.example.android_study.kotlin.coroutine

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.coroutine.cancel.CoroutineCancelAy
import com.example.android_study.kotlin.coroutine.coroutineContext.CoroutineContextActivity
import com.example.android_study.kotlin.coroutine.base.sample_for_debug.CoroutineSampleActivity
import com.example.android_study.kotlin.coroutine.base.understandCoroutine.CoroutineUnderstandActivity
import com.example.android_study.kotlin.coroutine.coroutineLite.CoroutineLiteActivity
import com.example.android_study.kotlin.coroutine.exception.CoroutineExceptionAy
import com.example.android_study.kotlin.coroutine.scope.CoroutineScopeAy
import com.example.android_study.kotlin.coroutine.startMode.CoroutineStartModeAy
import kotlinx.android.synthetic.main.activity_coroutines_ay.*

@ExperimentalStdlibApi
class CoroutinesAy : BaseActivity() {
    val list = listOf(
        Entry("1. 标准库实现最简单的协程", CoroutineSampleActivity::class.java,"用于debug源码"),
        Entry("2. 挂起与恢复", CoroutineUnderstandActivity::class.java,"抛开协程探究挂起与恢复的含义"),
        Entry("3. 协程上下文", CoroutineContextActivity::class.java),
        Entry("4. 根据标准库自实现协程框架", CoroutineLiteActivity::class.java,"霍丙乾开发的仿官方协程框架"),
        Entry("5. 取消", CoroutineCancelAy::class.java),
        Entry("6. 异常处理", CoroutineExceptionAy::class.java),
        Entry("7. 协程作用域", CoroutineScopeAy::class.java),
        Entry("8. 启动模式", CoroutineStartModeAy::class.java),
    )

    override fun getLayoutId() = R.layout.activity_coroutines_ay

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, list)
        lifecycleScope
    }


}

