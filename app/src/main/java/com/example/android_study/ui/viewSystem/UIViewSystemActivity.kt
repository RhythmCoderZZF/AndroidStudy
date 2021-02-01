package com.example.android_study.ui.viewSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.Entry
import com.example.android_study.ui.viewSystem.choreographer.UIChoreographerActivity
import kotlinx.android.synthetic.main.activity_u_i_view_system.*

class UIViewSystemActivity : BaseActivity() {

    override fun getLayoutId()=R.layout.activity_u_i_view_system

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(rv, listOf(
                Entry("1 Choreographer", UIChoreographerActivity::class.java)
        ))
    }
}