package com.example.network

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {
    @GET("{type}/{number}")
    fun getFact(@Path("number") number : Int, @Path("type") type : String) : ApiResult<String>
}