package com.example.profilematcher.ui.profiles

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profilematcher.data.UserInfoRepository
import com.example.profilematcher.data.model.LocalUserInfo
import com.example.profilematcher.data.model.Result
import com.example.profilematcher.data.model.UpdateItemInfo
import com.example.profilematcher.data.network.NetworkServiceManager
import com.example.profilematcher.data.model.UserInfo
import com.example.profilematcher.util.RequestStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/*
        Viewmodel class to handle requests to database and remote server
 */
class ProfilesViewModel@Inject constructor(private val userInfoRepository: UserInfoRepository) : ViewModel() {

        private val compositeDisposable : CompositeDisposable = CompositeDisposable()

        private val response: MutableLiveData<List<LocalUserInfo>> = MutableLiveData()
        private val status: MutableLiveData<RequestStatus> = MutableLiveData()
        val visibilityStatus: MutableLiveData<UpdateItemInfo> = MutableLiveData()

        private lateinit var tempUserInfo: UpdateItemInfo


        fun getResponse(): MutableLiveData<List<LocalUserInfo>> {
                return response
        }

        fun getStatus(): MutableLiveData<RequestStatus> {
                return status
        }

        fun getProfileStatus(): MutableLiveData<UpdateItemInfo> {
                return visibilityStatus
        }


        /*
            Method used for fetching data from api
         */
        fun fetchUserProfiles() {

                if(status.value == RequestStatus.SUCCESS) {
                        return
                }
                status.value = RequestStatus.IN_PROGRESS

                compositeDisposable.add(userInfoRepository.getUserInfo()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(this::onSuccess, this::onFailure)
                )

        }



        /*
            Method used for fetching update user status into local database
        */
        fun updateUserStatus(position : Int, userInfo: LocalUserInfo){

                tempUserInfo = UpdateItemInfo(position, userInfo)

                compositeDisposable.add(
                        userInfoRepository.updateUser(userInfo)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::onUpdateSuccess , this::onUpdateFailure)
                )
        }


        private fun onSuccess(response: List<LocalUserInfo>){
                this.response.value = response
                status.value = RequestStatus.SUCCESS
        }

        private fun onFailure(error : Throwable){
                this.response.value = null
                Log.d("ViewModel",error.stackTrace.toString())
                status.value = RequestStatus.FAILURE

        }

        private fun onUpdateSuccess(){
                this.visibilityStatus.postValue( tempUserInfo)
        }

        private fun onUpdateFailure(error : Throwable){
                Log.d("ViewModel",error.message.toString())
                status.value = RequestStatus.FAILURE

        }

        override fun onCleared() {
                super.onCleared()
                compositeDisposable.clear()
        }

}