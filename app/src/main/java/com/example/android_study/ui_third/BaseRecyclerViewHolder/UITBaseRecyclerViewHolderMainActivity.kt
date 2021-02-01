package com.example.android_study.ui_third.BaseRecyclerViewHolder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_study.R
import com.example.android_study.ui_third.BaseRecyclerViewHolder.adapter.TimePickAdapter
import kotlinx.android.synthetic.main.activity_u_i_t_base_recycler_view_holder_main.*

class UITBaseRecyclerViewHolderMainActivity : AppCompatActivity() {

    private val datas = (0..100).toList()
    private lateinit var mAdapter: TimePickAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u_i_t_base_recycler_view_holder_main)

        rv.apply {
            mAdapter = TimePickAdapter()
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@UITBaseRecyclerViewHolderMainActivity)
        }
        mAdapter.addData(datas)
        mAdapter.addFooterView(layoutInflater.inflate(R.layout.item_recycler_view_footer,null))
        mAdapter.addHeaderView(layoutInflater.inflate(R.layout.item_recycler_view_footer,null))
    }
}