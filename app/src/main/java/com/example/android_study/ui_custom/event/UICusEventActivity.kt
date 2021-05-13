package com.example.android_study.ui_custom.event

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui_custom.event.conflict.ConflictAActivity
import com.example.android_study.ui_custom.event.conflict.ConflictBActivity
import com.example.android_study.ui_custom.event.dispatchTouchEvent.*
import kotlinx.android.synthetic.main.activity_rv.*

class UICusEventActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 没人消费", BNotConsumeActivity::class.java),
                Entry("1.1 B消费", BConsumeActivity::class.java),
                Entry("2 A拦截down,不消费", AInterceptDownActivity::class.java),
                Entry("2.1 A拦截down,消费", AInterceptDownConsumeActivity::class.java),
                Entry("3 A拦截move,不消费", AInterceptMoveActivity::class.java),
                Entry("3.1 A拦截move,消费", AInterceptMoveConsumeActivity::class.java),
                Entry("4 滑动冲突(外部拦截)", ConflictAActivity::class.java),
                Entry("4 滑动冲突(内部拦截)", ConflictBActivity::class.java),
                Entry("5工具类 Scroller", ConflictBActivity::class.java),

        ))
    }
}