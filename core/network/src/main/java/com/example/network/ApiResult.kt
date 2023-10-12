package com.example.network

import com.example.model.IsEven


sealed class ApiResult<T : Any> {
    data class Success<T : Any>(val data: T) : ApiResult<T>()
    data class Error<T : Any>(val code : Int,  val msg : String) : ApiResult<T>()
    data class Exception<T : Any>(val e : Throwable) : ApiResult<T>()
}