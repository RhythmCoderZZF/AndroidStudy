package com.example.android_study.android.fragment._lifecycle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study._base.BaseFragment
import kotlinx.android.synthetic.main.fragment_ins.*

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/28
 * Description:
 */
open class FragmentIns(val content: String) : BaseFragment() {
    init {
        showLifecycle = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_ins, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        v_content.text = "$content"
        v_start_new_activity.setOnClickListener {
            startActivity(Intent(context, FragmentDemoAy::class.java))
        }
        v_remove_fragment.setOnClickListener {
            removeFragment()
        }
    }


    private fun removeFragment() {
        if (this.isAdded) {
            (context as BaseActivity).supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
        }
    }


}