package com.example.android_study.jetpack.room

import android.os.Bundle
import android.view.View
import com.example.android_study.R
import com.example.android_study._base.BaseActivity
import com.example.android_study.jetpack.room.room.*

/**
 * Room使用database navigator要注意
 * 1. 导出db文件(-shm、-wal文件也需要导出）并建立新的连接，否则观测的数据不会变化
 * 2. 线断开connection刷新数据库再建立连接才能查看数据变化
 */
class RoomAy : BaseActivity() {
    private lateinit var studentDao: StudentDao
    private lateinit var addressDao: AddressDao

    override fun getLayoutId(): Int {
        return R.layout.activity_room_ay
    }

    override fun initViewAndData(savedInstanceState: Bundle?) {
        studentDao = AppDatabase.getDatabase(this).studentDao()
        addressDao = AppDatabase.getDatabase(this).addressDao()
    }

    fun select(view: View?) {
        val queryAll = studentDao.queryAll()
        showToast(queryAll.toString())
    }

    fun select1(view: View?) {
        val queryAll1 = addressDao.queryAll()
        showToast(queryAll1.toString())
    }

    fun insert(view: View?) {
        val student = Student(name = "xxx", age = (18..30).random(), score = (0..100).random().toFloat())
        studentDao.insertStudent(student)
        select(null)
    }
    fun insert1(view: View?) {
        val city = Address(city = "浙江省")
        addressDao.insertAddress(city)
        select1(null)
    }

    fun clear(view: View?) {
        studentDao.clear()
        addressDao.clear()
    }

    fun update(view: View?) {
        studentDao.updateStudent(Student(id = (1..100).random(), name = "xxx", age = (18..30).random(), score = (0..100).random().toFloat()))
        select(null)
    }
}