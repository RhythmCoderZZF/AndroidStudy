package com.example.android_study.jetpack.viewmodel.isInstance

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import kotlinx.android.synthetic.main.activity_view_model_test1.*

class ViewModelTestActivity : BaseActivity(){
    private val mViewModel:TestViewModel by viewModels()
    override fun getLayoutId()=R.layout.activity_view_model_test1

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        window.decorView.post(this)
        button6.setOnClickListener {
            startActivity(Intent(this,ViewModelTestActivity::class.java))
        }
    }

    override fun run() {
        CmdUtil.v("ViewModel地址:$mViewModel")
    }
}