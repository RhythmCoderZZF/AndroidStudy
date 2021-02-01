package com.example.android_study.jetpack.demo_wanandroid.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_study._base.App

/**
 * Author:create by RhythmCoder
 * Date:2020/8/18
 * Description:
 */
@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao

    companion object {
        @Volatile
        private var instance: HistoryDatabase? = null

        operator fun invoke() = instance ?: synchronized(HistoryDatabase::class) {
            instance ?: createDatabase()
        }


        private fun createDatabase() {
            Room.databaseBuilder(App.instance, HistoryDatabase::class.java, "wanandroid_history_db.db").build().apply { instance = this }
        }
    }
}