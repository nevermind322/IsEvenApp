package com.example.data

import com.example.network.ApiResult

sealed class RepoResult<out T : Any> {
    data class Success<T : Any>(val data: T) : RepoResult<T>()
    data class Error(val msg: String) : RepoResult<Nothing>()
}

internal fun <T : Any> ApiResult<T>.toRepoResult() =
    when (this) {
        is ApiResult.Success -> RepoResult.Success(data)
        is ApiResult.Error -> RepoResult.Error(msg)
        is ApiResult.Exception -> RepoResult.Error(e.message ?: "Unknown error!")
    }
