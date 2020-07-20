package com.example.android_study.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_study.base.cmd.CmdUtil

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/28
 * Description:
 */
open class BaseFragment : Fragment() {
    private val str = javaClass.simpleName
    protected var showLifecycle = false //向cmd打印生命周期


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (showLifecycle)
            CmdUtil.e("$str：onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (showLifecycle)
            CmdUtil.e("$str：onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (showLifecycle)
            CmdUtil.e("$str：onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if (showLifecycle)
            super.onActivityCreated(savedInstanceState)
        CmdUtil.e("$str：onActivityCreated")
    }

    override fun onStart() {
        if (showLifecycle)
            super.onStart()
        CmdUtil.e("$str：onStart")
    }

    override fun onResume() {
        super.onResume()
        if (showLifecycle)
            CmdUtil.e("$str：onResume")
    }

    override fun onPause() {
        super.onPause()
        if (showLifecycle)
            CmdUtil.e("$str：onPause")
    }

    override fun onStop() {
        super.onStop()
        if (showLifecycle)
            CmdUtil.e("$str：onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (showLifecycle)
            CmdUtil.e("$str：onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (showLifecycle)
            CmdUtil.e("$str：onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        if (showLifecycle)
            CmdUtil.e("$str：onDetach")
    }
}