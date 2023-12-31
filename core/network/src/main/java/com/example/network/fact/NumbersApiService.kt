package com.example.network.fact

import com.example.network.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    @GET("{number}/{type}")
    suspend fun getFact(@Path("number") number : Int, @Path("type") type : String) : ApiResult<String>
}