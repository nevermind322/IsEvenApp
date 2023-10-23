package com.example.data.even

import com.example.data.toRepoResult
import com.example.network.even.NetworkEvenDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EvenRepository @Inject constructor(private val evenNetworkDataSource: NetworkEvenDataSource) {
    suspend fun isEven(number: Int) = evenNetworkDataSource.isEven(number).toRepoResult()
}