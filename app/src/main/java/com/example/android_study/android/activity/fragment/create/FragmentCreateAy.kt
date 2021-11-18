package com.example.android_study.android.activity.fragment.create

import android.os.Bundle
import androidx.fragment.app.commit
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.android.activity.fragment.SampleFragment
import kotlinx.android.synthetic.main.activity_fragment_create.*

/**
 * Author:create by RhythmCoder
 * Date:2021/7/21
 * Description:
 */
class FragmentCreateAy : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_fragment_create

    override fun initViewAndData(savedInstanceState: Bundle?) {
        btn_add_fragment.setOnClickListener {
            val fragment=SampleFragment.newInstance("代码")
            supportFragmentManager.commit {
                setReorderingAllowed(false)
                add(R.id.container1,fragment)
            }
            supportFragmentManager.commit {
                setReorderingAllowed(false)
                add(R.id.container2,SampleFragment.newInstance("代码2"))

            }
            supportFragmentManager.commit {
                setReorderingAllowed(false)
                remove(fragment)
            }
        }
    }
}