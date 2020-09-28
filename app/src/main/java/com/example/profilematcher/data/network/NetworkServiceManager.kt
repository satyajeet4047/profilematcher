package com.example.profilematcher.data.network

import com.example.profilematcher.data.model.Result
import io.reactivex.Observable
import javax.inject.Inject

class NetworkServiceManager @Inject constructor(private val networkService: NetworkService) {

   fun fetchData(result : Int) : Observable<Result>{
       return  networkService.fetchData(result)
   }

}