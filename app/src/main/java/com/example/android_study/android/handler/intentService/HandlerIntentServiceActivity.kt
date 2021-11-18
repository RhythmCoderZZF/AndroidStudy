package com.example.android_study.android.handler.intentService

import android.app.IntentService
import android.content.Intent
import android.os.*
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_handler_intent_service.*

class HandlerIntentServiceActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_handler_intent_service

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()


        btn_start.setOnClickListener {
           startService(Intent(this,HelloIntentService()::class.java).apply {
               putExtra("name","111")
           })
        }
        btn_stop.setOnClickListener {
           stopService(Intent(this,HelloIntentService()::class.java))
        }
    }

    /**
     * A constructor is required, and must call the super [android.app.IntentService.IntentService]
     * constructor with a name for the worker thread.
     */
    private inner class HelloIntentService : IntentService("HelloIntentService") {

        /**
         * The IntentService calls this method from the default worker thread with
         * the intent that started the service. When this method returns, IntentService
         * stops the service, as appropriate.
         */
        override fun onHandleIntent(intent: Intent?) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                val name=intent?.getStringExtra("name")
                CmdUtil.v("$name:开始执行...")
                Thread.sleep(5000)
                CmdUtil.v("$name:执行结束！")
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

        }
    }
}