package com.example.android_study.android.data_and_file._ContentProvider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.android_study.android.database.sqlLite.db.MyDatabaseHelper

/**
 * Author:create by RhythmCoder
 * Date:2021/3/11
 * Description:
 */
class MyContentProvider : ContentProvider() {
    private val mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private lateinit var mDBHelper: MyDatabaseHelper
    companion object {
        const val PATH_STUDENT = "Student"
        const val CODE_STUDENT = 1
        const val PATH_STUDENT_ID = "Student/#"
        const val CODE_STUDENT_ID = 2
    }

    override fun onCreate(): Boolean {
        mDBHelper = MyDatabaseHelper(context)
        context?.apply {
            val authority = "$packageName.mycontentporvider"
            mUriMatcher.addURI(authority, PATH_STUDENT, CODE_STUDENT)
            mUriMatcher.addURI(authority, PATH_STUDENT_ID, CODE_STUDENT_ID)
        }
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val db = mDBHelper.writableDatabase
        when (mUriMatcher.match(uri)) {
            CODE_STUDENT -> {
                return db.query("Student", projection, selection, selectionArgs, null, null, sortOrder)
            }
            CODE_STUDENT_ID -> {
                val id = ContentUris.parseId(uri)
                return db.query("Student", projection, "id=?", arrayOf("$id"), null, null, sortOrder)
            }
        }
        return null
    }

    override fun getType(uri: Uri): String? {

        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }
}