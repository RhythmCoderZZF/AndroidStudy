package com.example.android_study.android.activity.fragment.fragmentManager

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.cmd.CmdUtil
import com.example.android_study.android.activity.fragment.SampleFragment
import kotlinx.android.synthetic.main.activity_fragment_manager.*

class FragmentManagerActivity : BaseActivity() {
    private val fragments = mutableListOf<Fragment>()
    private var temp=0
    private var temp2=0
    init {
        showLifecycle=true
        repeat(5) {
            fragments.add(SampleFragment.newInstance(it.toString()))
        }
    }

    override fun getLayoutId() = R.layout.activity_fragment_manager

    override fun initViewAndData(savedInstanceState: Bundle?) {
        add.setOnClickListener {
            if (temp > 4) {
                showToast("不能添加再多")
                return@setOnClickListener
            }
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.add(R.id.fragmentContainer, fragments[temp])
            beginTransaction.addToBackStack("add")
            beginTransaction.commit()
            temp++
        }
        pop.setOnClickListener {
            if (temp == 0) {
                return@setOnClickListener
            }
            supportFragmentManager.popBackStack("add",1)
            temp--
        }

        supportFragmentManager.addOnBackStackChangedListener {
            CmdUtil.e("BackStackChanged!")
        }
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

        replace.setOnClickListener {
            if (temp2 > 4) {
                showToast("不能添加再多")
                return@setOnClickListener
            }
            val beginTransaction = supportFragmentManager.beginTransaction()
            beginTransaction.replace(R.id.fragmentContainer2, fragments[temp2])
            beginTransaction.addToBackStack("replace")
            beginTransaction.commit()
            temp2++
        }
        pop2.setOnClickListener {
            if (temp2 == 0) {
                return@setOnClickListener
            }
            supportFragmentManager.popBackStack("replace",1)
            temp2--
        }
    }
}