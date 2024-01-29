package com.example.shifttestexample.api

import com.example.shifttestexample.pojo.results
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("api/?results=20")
    fun loadUsers(): Single<results>
}