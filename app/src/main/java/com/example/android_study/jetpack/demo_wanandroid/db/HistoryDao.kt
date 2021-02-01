package com.example.android_study.jetpack.demo_wanandroid.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 */
@Dao
interface HistoryDao {
    @Insert
    fun insert(history: History)

    @Query("SELECT * FROM History")
    fun getAllHistory(): LiveData<List<History>>

    @Delete
    fun delete(history: History)
}