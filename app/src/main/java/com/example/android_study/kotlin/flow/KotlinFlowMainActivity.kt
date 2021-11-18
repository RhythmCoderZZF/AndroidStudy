package com.example.android_study.kotlin.flow

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.kotlin.flow.flow_code.KotlinFlowCodeActivity
import com.example.android_study.kotlin.flow.flow_livedata.KotlinFlowAndLiveDataActivity
import com.example.android_study.kotlin.flow.base.KotlinFlowBaseActivity
import com.example.android_study.kotlin.flow.cancel.KotlinFlowCancelActivity
import com.example.android_study.kotlin.flow.completion.KotlinFlowCompletionActivity
import com.example.android_study.kotlin.flow.exception.KotlinFlowExceptionActivity
import com.example.android_study.kotlin.flow.operator.KotlinFlowOperatorMainActivity
import com.example.android_study.kotlin.flow.stateFlow_sharedFlow.KotlinStateFlowSharedFlowActivity
import com.example.android_study.kotlin.flow.switchThread.kotlinFlowSwitchThreadActivity
import kotlinx.android.synthetic.main.activity_rv.*

class KotlinFlowMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1 核心知识", KotlinFlowBaseActivity::class.java, "基础使用，上下文切换，背压"),
                Entry("- 最简单的调试源码案例", KotlinFlowCodeActivity::class.java),
                Entry("2 操作符", KotlinFlowOperatorMainActivity::class.java),
                Entry("3 上下文切换", kotlinFlowSwitchThreadActivity::class.java),
                Entry("4 异常处理", KotlinFlowExceptionActivity::class.java),
                Entry("5 完成处理", KotlinFlowCompletionActivity::class.java),
                Entry("6 取消处理", KotlinFlowCancelActivity::class.java),
                Entry("7 Flow和LiveData的转换", KotlinFlowAndLiveDataActivity::class.java),
                Entry("8 StateFlow、SharedFlow", KotlinStateFlowSharedFlowActivity::class.java),
            )
        )
    }
}