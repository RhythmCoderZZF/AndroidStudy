package com.example.android_study.event_system.event

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_util.Scroller.ScrollerMainActivity
import com.example.android_study.event_system.event_util.ViewDragHelper.ViewDragHelperMainActivity
import com.example.android_study.event_system.event.conflict.ConflictAActivity
import com.example.android_study.event_system.event.conflict.ConflictBActivity
import com.example.android_study.event_system.event.dispatchTouchEvent.*
import kotlinx.android.synthetic.main.activity_rv.*

class UICusEventActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 没人消费", BNotConsumeActivity::class.java),
                Entry("1.1 B仅消费down", BConsumeActivity::class.java),
                Entry("1.2 B消费所有事件", BConsumeAllActivity::class.java),
                Entry("2 A拦截down,不消费", AInterceptDownActivity::class.java),
                Entry("2.1 A拦截down,仅消费down", AInterceptDownConsumeActivity::class.java),
                Entry("3 (ABC)B拦截move,不消费", AInterceptMoveActivity::class.java),
                Entry("3.1 (ABC)B拦截move,消费", AInterceptMoveConsumeActivity::class.java),
                Entry("4 滑动冲突(外部拦截)", ConflictAActivity::class.java),
                Entry("4.1 滑动冲突(内部拦截)", ConflictBActivity::class.java),
                Entry("5 工具类 Scroller", ScrollerMainActivity::class.java),
                Entry("6 工具类 ViewDragHelper", ViewDragHelperMainActivity::class.java),

        ))
    }
}