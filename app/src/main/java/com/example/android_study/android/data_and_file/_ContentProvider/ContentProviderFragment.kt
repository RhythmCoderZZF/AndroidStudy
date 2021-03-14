package com.example.android_study.android.data_and_file._ContentProvider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_study.R
import com.example.android_study._base.BaseFragment

/**
 * Author:create by RhythmCoder
 * Date:2021/3/11
 * Description:
 */
class ContentProviderFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_d_f_content_provider,container,false)
    }
}