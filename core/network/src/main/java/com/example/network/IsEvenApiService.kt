package com.example.network

import com.example.model.IsEven
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IsEvenApiService {
    @GET("iseven/{number}")
    suspend fun isEven(@Path("number") number : Int) : ApiResult<IsEven>
}

