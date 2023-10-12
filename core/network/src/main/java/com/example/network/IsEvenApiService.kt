package com.example.network

import retrofit2.http.GET
import retrofit2.http.Path

interface IsEvenApiService {

    @GET("iseven/{number}")
    fun isEven(@Path("number") number : Int)
}

