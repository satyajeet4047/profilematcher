package com.example.profilematcher.data.network

import com.example.profilematcher.data.model.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/api")
    fun fetchData(@Query("results") results : Int): Observable<Result>

}
