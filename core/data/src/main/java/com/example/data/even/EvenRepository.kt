package com.example.data.even

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EvenRepository @Inject constructor(private val evenNetworkDataSource: NetworkEvenDataSource) {
    suspend fun isEven(number: Int) = evenNetworkDataSource.isEven(number)
}