package com.example.android_study.android.database.sqlLite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.android_study._base.cmd.CmdUtil

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/10
 * Description:
 */
class MyDatabaseHelper(context: Context?, val name: String = "sql_db.db", val version: Int = 2) : SQLiteOpenHelper(context, name, null, version) {
    private val createUser = """
    create table Student(
        id integer primary key autoincrement,
        name text,
        age integer,
        score real
    )
    """.trimIndent()
    private val createAddress = """
    create table Address(
        id integer primary key autoincrement,
        city text,
        IdCard integer
    )
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase) {
        CmdUtil.v("MyDatabaseHelper onCreate")
        db.execSQL(createUser)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        CmdUtil.v("MyDatabaseHelper onUpgrade")
        if (oldVersion <= 2) {
            db.execSQL(createAddress)
        }
        onCreate(db)
    }
}