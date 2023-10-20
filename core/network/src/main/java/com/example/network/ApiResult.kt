package com.example.network

sealed class ApiResult< out T : Any> {
    data class Success<T : Any>(val data: T) : ApiResult<T>()
    data class Error(val code : Int,  val msg : String) : ApiResult<Nothing>()
    data class Exception(val e : Throwable) : ApiResult<Nothing>()
}