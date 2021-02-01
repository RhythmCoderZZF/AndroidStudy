package com.example.android_study.jetpack.demo_wanandroid.ui.knowledge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_study.R

class KnowledgeFragment : Fragment() {

    companion object {
        fun newInstance() = KnowledgeFragment()
    }

    private lateinit var viewModel: KnowledgeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.knowledge_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(KnowledgeViewModel::class.java)
    }
}