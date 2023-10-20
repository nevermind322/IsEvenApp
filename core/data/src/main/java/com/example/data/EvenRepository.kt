package com.example.data

import com.example.network.even.IsEvenDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EvenRepository @Inject constructor(private val evenNetworkDataSource: IsEvenDataSource) {
    suspend fun isEven(number: Int) = evenNetworkDataSource.isEven(number)
}