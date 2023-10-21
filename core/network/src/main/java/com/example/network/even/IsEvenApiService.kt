package com.example.network.even

import com.example.model.IsEven
import com.example.network.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

interface IsEvenApiService {
    @GET("iseven/{number}")
    suspend fun isEven(@Path("number") number : Int) : ApiResult<IsEven>
}

