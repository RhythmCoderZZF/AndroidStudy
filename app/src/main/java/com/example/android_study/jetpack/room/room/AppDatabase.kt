package com.example.android_study.jetpack.room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/11
 * Description:
 * Room Database
 * 如果您的应用在单个进程中运行，在实例化 AppDatabase 对象时应遵循单例设计模式。
 * 每个 RoomDatabase 实例的成本相当高，而您几乎不需要在单个进程中访问多个实例。
 */
@Database(version = 2, entities = [Student::class, Address::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun addressDao(): AddressDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null


        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("""
                    create table Address(
                    id integer primary key autoincrement not null,
                    city text not null
                )""".trimMargin())
            }

        }

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            //单例
            instance?.let {
                return it
            }
            //!context一定要使用ApplicationContext
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "room_db.db")
                    .allowMainThreadQueries()//主线程操作数据库，只能在测试环境使用
                    .addMigrations(MIGRATION_1_2)//数据库升级
                    .build().apply { instance = this }

        }
    }
}