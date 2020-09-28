package com.example.profilematcher.data.localdb

import androidx.room.*
import com.example.profilematcher.data.model.LocalUserInfo
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM  UserInfo")
    fun getUserInfo() : Observable<List<LocalUserInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(dbUserInfo: LocalUserInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfoList(userInfoList : List<LocalUserInfo>)

    @Update
    fun updateSUserData(dbUserInfo: LocalUserInfo) : Completable
}