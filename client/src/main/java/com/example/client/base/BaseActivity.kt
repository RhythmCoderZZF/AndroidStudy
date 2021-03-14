package com.example.client.base

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Author:create by RhythmCoder
 * Date:2021/3/11
 * Description:
 */
open class BaseActivity : AppCompatActivity() {

    protected fun setRecyclerView(rv: RecyclerView, list: List<Entry>) {
        rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.adapter = RvAdapter(list)
    }
}