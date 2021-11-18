package com.example.android_study.android.handler

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.android.handler.asyncTask.HandlerAsyncTaskActivity
import com.example.android_study.android.handler.intentService.HandlerIntentServiceActivity
import com.example.android_study.android.handler.threadLocal.HandlerThreadLocalActivity
import com.example.android_study.android.handler.handlerThread.HandlerThreadActivity
import kotlinx.android.synthetic.main.activity_rv.*

class HandlerMainActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_rv

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 ThreadLocal", HandlerThreadLocalActivity::class.java,"ThreadLocal在Handler体系中的重要作用"),
                Entry("2 Handler Thread", HandlerThreadActivity::class.java,"子线程创建使用handler"),
                Entry("3 AsyncTask", HandlerAsyncTaskActivity::class.java,"AsyncTask"),
                Entry("4 IntentSercice", HandlerIntentServiceActivity::class.java,"IntentSercice"),
        ))
    }
}