package com.example.android_study.Java.MemoryModel.String

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import kotlinx.android.synthetic.main.activity_jmm_string.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/5
 * Description:
 */
class JavaStringMMActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_jmm_string
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        val i1=40
        val i2=Integer(40).toInt()
        tv1.text = """
            ${i1===i2}
        """.trimIndent()
    }
}