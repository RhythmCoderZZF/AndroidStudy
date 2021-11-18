package com.example.android_study.ui.recyclerView.positioin

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.adapter.OnItemClickListener2
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.ui.recyclerView.StringAdapter
import kotlinx.android.synthetic.main.activity_rv.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/26
 * Description:
 */
class RVPositionMainActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_rv
    override fun initViewAndData(savedInstanceState: Bundle?) {
        rv.apply {
            adapter = StringAdapter().apply {
                itemListener2 = OnItemClickListener2 { position, holder, data ->
                    CmdUtil.v("layout pos:${holder?.layoutPosition}")
                    CmdUtil.i("bind pos:${holder?.bindingAdapterPosition}")
                    CmdUtil.i("abs pos:${holder?.absoluteAdapterPosition}")
                }
            }
            layoutManager = LinearLayoutManager(this@RVPositionMainActivity)
        }
    }
}