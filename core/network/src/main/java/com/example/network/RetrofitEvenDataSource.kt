package com.example.network

import com.example.data.NetworkEvenDataSource
import com.example.data.RepoResult
import com.example.network.even.IsEvenApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitEvenDataSource @Inject constructor(private val api: IsEvenApiService) :
    NetworkEvenDataSource {

    override suspend fun isEven(number: Int) = withContext(Dispatchers.IO) {

        when (val res = api.isEven(number)) {
            is ApiResult.Success -> RepoResult.Success(res.data)
            is ApiResult.Error -> RepoResult.Error(res.msg)
            is ApiResult.Exception -> RepoResult.Error(res.e.message ?: "Unknown error!")
        }
     }

}