package com.example.android_study._android._database._sqlLite

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import androidx.core.content.contentValuesOf
import com.example.android_study.R
import com.example.android_study._android._database._sqlLite.db.MyDatabaseHelper
import com.example.android_study.base.BaseActivity
import com.example.android_study.base.cmd.CmdUtil
import com.example.android_study.util.ToolbarHelper

/**
 * 原生使用database navigator
 *  1. save as db文件到外部目录，建立连接，
 *  2. 覆盖db文件需要先断开连接，再reload Schemas
 */
class SQLiteAy : BaseActivity(), Runnable {
    private lateinit var myDatabaseHelper: MyDatabaseHelper
    private lateinit var db: SQLiteDatabase

    override fun getLayoutId(): Int {
        return R.layout.activity_sql_lite_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        CmdUtil.showWindow()
        ToolbarHelper.setBar(this, "SQLite")
        window.decorView.postDelayed(this, 200)
    }

    override fun run() {
        myDatabaseHelper = MyDatabaseHelper(this, "sql_db.db", 2)
//      MyDatabaseHelper(this, "User.db", 1).readableDatabase    只读


    }


    fun insert(view: View) {
        db = myDatabaseHelper.writableDatabase
        val age = (18..30).random()
        val score = (0..100).random().toFloat()

        db.beginTransaction()       //事务
        try {
            if (age > 20) throw NullPointerException()
            val contentValuesOf = contentValuesOf("name" to "XXX", "age" to "$age", "score" to "$score")
            db.insert("Student", null, contentValuesOf)
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            e.printStackTrace()
            showToast("空指针异常：age=$age score=$score")
        } finally {
            db.endTransaction()
        }
    }

    fun update(view: View) {
        db = myDatabaseHelper.writableDatabase
    }

    fun delete(view: View) {
        db = myDatabaseHelper.writableDatabase
        db.delete("Student", null, null)
    }

    fun select(view: View) {
        val sb = StringBuilder()
        db = myDatabaseHelper.writableDatabase
        val cursor = db.query("Student", null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val age = cursor.getInt(cursor.getColumnIndex("age"))
                val score = cursor.getFloat(cursor.getColumnIndex("score"))
                sb.appendln("$name $age $score")
            } while (cursor.moveToNext())
        }
        cursor.close()
        showToast(sb.toString())
    }
}