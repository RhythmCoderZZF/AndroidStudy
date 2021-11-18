package com.example.android_study.ui.materialDesign.bug

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_study.R
import com.example.android_study._base.BaseFragment
import com.example.android_study.ui.recyclerView.gesture.adapter.GestureRvAdapter

/**
 * Author:create by RhythmCoder
 * Date:2021/1/27
 * Description:
 */
class BugFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_rv, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv)
        val data= mutableListOf<String>()
        repeat(100){
            data.add(it.toString())
        }
        recyclerView.adapter=GestureRvAdapter(data)
    }
}