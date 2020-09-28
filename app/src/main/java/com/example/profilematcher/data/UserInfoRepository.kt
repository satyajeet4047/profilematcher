package com.example.profilematcher.data

import com.example.profilematcher.data.localdb.UserInfoDao
import com.example.profilematcher.data.model.LocalUserInfo
import com.example.profilematcher.data.network.NetworkServiceManager
import com.example.profilematcher.util.NetworkUtil
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject


class UserInfoRepository @Inject constructor(private val networkServiceManager: NetworkServiceManager,
                                             private val userInfoDao: UserInfoDao, private val networkUtil: NetworkUtil) {

    fun getUserInfo() : Observable<List<LocalUserInfo>> {
        return when(networkUtil.isConnectedToInternet()) {
            true -> Observable.concatArrayEager(getLocalUserInfo(), getRemoteUserInfo())
            false -> getLocalUserInfo()
        }
    }

    private fun getLocalUserInfo() : Observable<List<LocalUserInfo>>{
        return userInfoDao.getUserInfo()
    }

    private fun getRemoteUserInfo() : Observable<MutableList<LocalUserInfo>>? {
        return networkServiceManager.fetchData(10)
            .flatMapIterable { it.userList}
            .map {
                LocalUserInfo(0,it.gender,"${it.name.firstName} ${it.name.lastName}",it.dob.age,
                    it.picture.url,"${it.location.state}, ${it.location.country}",it.login.uuid) }
            .doOnNext { userInfoDao.insertUserData(it) }
            .toList()
            .toObservable()
    }

    fun updateUser(userInfo: LocalUserInfo) : Completable{
      return  userInfoDao.updateSUserData(userInfo)
    }
}