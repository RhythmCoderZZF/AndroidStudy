package com.example.android_study._jetpack._room.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Author: create by RhythmCoder
 * Date: 2020/7/11
 * Description: Room定义的实体类
 */
@Entity
data class Address(@PrimaryKey(autoGenerate = true) val id: Int = 0, val city: String)