package com.example.profilematcher.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.profilematcher.data.model.LocalUserInfo

@Database(entities = arrayOf(LocalUserInfo::class), version = 1,exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getUserInfoDao() : UserInfoDao
}