package com.example.android_study.other.bugly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.databinding.ActivityOtherBuglyBinding
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.activity_other_bugly.*

class OtherBuglyActivity : BaseActivity() {
    override fun getLayoutId()=R.layout.activity_other_bugly

    override fun initViewAndData(savedInstanceState: Bundle?) {
        button7.setOnClickListener {
//            CrashReport.testJavaCrash();
            throw NullPointerException()
        }
    }
}