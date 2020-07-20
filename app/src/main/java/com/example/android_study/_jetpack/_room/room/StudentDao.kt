package com.example.android_study._jetpack._room.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/11
 * Description:Room Dao
 */
@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Update
    fun updateStudent(student: Student)

    @Query("delete from Student")               //若要查询或用非实体类参数增删改数据，需要用@Query编写sql
    fun clear()

    @Query("select * from Student")             //若要查询或用非实体类参数增删改数据，需要用@Query编写sql
    fun queryAll(): List<Student>

}