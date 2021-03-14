package com.example.android_study._base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author: create by RhythmCoder
 * Date: 2020/6/28
 * Description:
 */
open class BaseFragment : Fragment() {
    open val TAG = "Fragment[${this.hashCode()}]"
    protected var showLifecycle = false //向cmd打印生命周期


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (showLifecycle)
            CmdUtil.i("$TAG：onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (showLifecycle)
            CmdUtil.i("$TAG：onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        if (showLifecycle)
            CmdUtil.i("$TAG：onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (showLifecycle) CmdUtil.i("$TAG：onViewCreated")

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (showLifecycle)
            CmdUtil.i("$TAG：onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        if (showLifecycle)
            CmdUtil.i("$TAG：onStart")
    }

    override fun onResume() {
        super.onResume()
        if (showLifecycle)
            CmdUtil.i("$TAG：onResume")
    }

    override fun onPause() {
        super.onPause()
        if (showLifecycle)
            CmdUtil.i("$TAG：onPause")
    }

    override fun onStop() {
        super.onStop()
        if (showLifecycle)
            CmdUtil.i("$TAG：onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (showLifecycle)
            CmdUtil.i("$TAG：onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (showLifecycle)
            CmdUtil.i("$TAG：onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        if (showLifecycle)
            CmdUtil.i("$TAG：onDetach")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (showLifecycle)
            CmdUtil.i("$TAG：onHiddenChanged->$hidden")
    }
}