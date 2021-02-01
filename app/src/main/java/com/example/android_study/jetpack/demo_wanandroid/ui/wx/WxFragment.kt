package com.example.android_study.jetpack.demo_wanandroid.ui.wx

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R

class WxFragment : Fragment() {

    companion object {
        fun newInstance() = WxFragment()
    }

    private lateinit var viewModel: WxViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.wx_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(WxViewModel::class.java)
    }

}