package com.example.client.android.content_provider

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.example.client.R
import com.example.client._base.BaseActivity
import kotlinx.android.synthetic.main.activity_android_content_provider.*

class AndroidContentProviderMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_content_provider)

        button.setOnClickListener {
            val uri = Uri.parse("content://com.example.android_study.mycontentporvider/Student")
            val cursor = contentResolver.query(uri, null, null, null, null)
            val listRes = mutableListOf<String>()
            when (cursor?.count) {
                0 -> {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show()
                }
                null -> {
                    Toast.makeText(this, "错误❌", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(cursor.getColumnIndex("name"))
                        val age = cursor.getInt(cursor.getColumnIndex("age"))
                        listRes.add("$name - $age")
                    }
                    Toast.makeText(this, "$listRes", Toast.LENGTH_SHORT).show()
                }
            }
            cursor?.close()
        }

        button1.setOnClickListener {
            var uri = Uri.parse("content://com.example.android_study.mycontentporvider/Student")
            uri = ContentUris.withAppendedId(uri, 1)
            val cursor = contentResolver.query(uri, null, null, null, null)
            val listRes = mutableListOf<String>()
            when (cursor?.count) {
                0 -> {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show()
                }
                null -> {
                    Toast.makeText(this, "错误❌", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(cursor.getColumnIndex("name"))
                        val age = cursor.getInt(cursor.getColumnIndex("age"))
                        listRes.add("$name - $age")
                    }
                    Toast.makeText(this, "$listRes", Toast.LENGTH_SHORT).show()
                }
            }
            cursor?.close()
        }

    }
}