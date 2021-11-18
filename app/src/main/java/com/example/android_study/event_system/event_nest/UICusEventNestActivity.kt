package com.example.android_study.event_system.event_nest

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.event_system.event_nest.nest.NestMainActivity
import com.example.android_study.event_system.event_nest.nestScrollViewNest.NestNestScrollViewMainActivity
import com.example.android_study.event_system.event_nest.scrollViewNest.NestScrollViewMainActivity
import kotlinx.android.synthetic.main.activity_rv.*

class UICusEventNestActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(
            rv, listOf(
                Entry("1.ScrollView嵌套冲突与嵌套滑动", NestScrollViewMainActivity::class.java),
                Entry("2.NestScrollView嵌套滑动", NestNestScrollViewMainActivity::class.java),
                Entry("3.自实现嵌套滑动布局SuspendedLayout", NestMainActivity::class.java),
            )
        )
    }
}