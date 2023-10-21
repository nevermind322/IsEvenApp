package com.example.data

sealed class RepoResult<out T : Any> {
    data class Success<T : Any>(val data : T) : RepoResult<T>()
    data class Error(val msg : String) : RepoResult<Nothing>()
}