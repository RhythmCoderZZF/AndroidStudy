package com.example.android_study.ui.recyclerView.gesture

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_study.R
import com.example.android_study.ui.recyclerView.gesture.adapter.GestureRvAdapter
import com.example.android_study.ui.recyclerView.gesture.core.ItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_item_touch_hel_recycler_view.*
import java.util.*

class UIRecyclerViewDragActivity : AppCompatActivity() {
    private val mDatas: MutableList<String> = ArrayList(40)
    private var adapter: GestureRvAdapter? = null

    init {
        for (i in 0..149) {
            val title = i.toString() + ""
            mDatas.add(title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_touch_hel_recycler_view)

        adapter = GestureRvAdapter(mDatas)

        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(rv)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        rv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }
}