package com.example.android_study.android._database

import android.os.Bundle
import com.example.android_study.R
import com.example.android_study.android._database._file.FileAy
import com.example.android_study.android._database._sp.SpAy
import com.example.android_study.android._database._sqlLite.SQLiteAy
import com.example.android_study._base.adapter.Entry
import com.example.android_study._base.BaseActivity

class MainDataBaseAy : BaseActivity() {
    private val list = mutableListOf(
            Entry("File文件存储", FileAy::class.java),
            Entry("Sp存储", SpAy::class.java),
            Entry("SQLite存储", SQLiteAy::class.java)
    )


    override fun getLayoutId(): Int {
        return R.layout.activity_main_data_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        setRecyclerView(findViewById(R.id.rv), list)
    }
}